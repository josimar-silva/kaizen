use std::collections::HashMap;

use rayon::prelude::*;
use regex::Regex;

use crate::domain::git::entities::{Commit, CommitData, CommitsByDate};
use crate::domain::kaizen::functions::analysis::AnalysisFiles;

pub fn parse_commits(
	commits: &[Commit],
	repo_path: &str,
	analysis_files: &AnalysisFiles,
) -> Result<CommitsByDate, regex::Error> {
	let commit_re = Regex::new(
		r"^(?P<type>algo|sysdes)\((?P<date>\d{4}-\d{2}-\d{2})\): (?P<title>.*)",
	)?;
	let notes_re = Regex::new(r"(?ms)^notes: (?P<notes>.*?)(?:^Ref\.:|^\w+:|\z)")?;
	let lang_re = Regex::new(r"(?m)^language: (?P<language>.*)")?;
	let ref_re = Regex::new(r"Ref\.:\s*(?P<reference>https?://\S+)")?;

	let intermediate_data: HashMap<String, Vec<(usize, CommitData)>> = commits
		.par_iter()
		.enumerate()
		.filter_map(|(index, commit)| {
			commit_re.captures(&commit.message).map(|commit_caps| {
				let date = commit_caps["date"].to_string();
				let title = commit_caps["title"].to_string();
				let type_of = commit_caps["type"].to_string();

				let mut notes = notes_re
					.captures(&commit.message)
					.map(|c| c["notes"].trim().to_string())
					.unwrap_or_default();

				let reference = ref_re
					.captures(&commit.message)
					.map(|c| c["reference"].trim().to_string());

				if let Some(ref_link) = &reference {
					notes = notes
						.replace(&format!("Ref.: {ref_link}"), "")
						.trim()
						.to_string();
				}

				let language = lang_re
					.captures(&commit.message)
					.map(|c| c["language"].to_string())
					.unwrap_or_default();

				let link =
					format!("https://github.com/{}/commit/{}", repo_path, commit.id);

				let analysis = analysis_files.get(&commit.id).cloned();

				(
					date,
					(index, CommitData {
						title,
						notes,
						link,
						language,
						type_of,
						reference,
						analysis,
					}),
				)
			})
		})
		.fold(
			HashMap::new,
			|mut acc: HashMap<String, Vec<(usize, CommitData)>>, (date, data)| {
				acc.entry(date).or_default().push(data);
				acc
			},
		)
		.reduce(HashMap::new, |mut a, b| {
			for (k, v) in b {
				a.entry(k).or_default().extend(v);
			}
			a
		});

	Ok(sort_and_map_commits_by_date(intermediate_data))
}

fn sort_and_map_commits_by_date(
	intermediate_data: HashMap<String, Vec<(usize, CommitData)>>,
) -> CommitsByDate {
	let mut commits_by_date_vec: Vec<(String, Vec<(usize, CommitData)>)> =
		intermediate_data.into_iter().collect();
	commits_by_date_vec.sort_by(|(date_a, _), (date_b, _)| {
		let date_a_parsed =
			chrono::NaiveDate::parse_from_str(date_a, "%Y-%m-%d").unwrap();
		let date_b_parsed =
			chrono::NaiveDate::parse_from_str(date_b, "%Y-%m-%d").unwrap();
		date_a_parsed.cmp(&date_b_parsed)
	});

	let mut commits_by_date: CommitsByDate = HashMap::new();
	for (date, mut indexed_commits) in commits_by_date_vec {
		indexed_commits.sort_by_key(|(index, _)| *index);
		let sorted_commits =
			indexed_commits.into_iter().map(|(_, data)| data).collect();
		commits_by_date.insert(date, sorted_commits);
	}

	commits_by_date
}

#[cfg(test)]
mod tests {
	use std::fs;
	use std::path::PathBuf;

	use tempfile::tempdir;

	use super::*;
	use crate::domain::git::entities::GitRepository;

	#[test]
	fn test_parse_commits() {
		let commits = vec![
			Commit {
				id:      String::from("abc000"),
				message: String::from("feat: some other feature"),
			},
			Commit {
				id:      String::from("abc001"),
				message: String::from(
					"algo(2025-09-08): Two Sum\nnotes: Solved with hash \
					 map.\nlanguage: Rust",
				),
			},
			Commit {
				id:      String::from("abc002"),
				message: String::from(
					"sysdes(2025-09-08): Web Crawler\nnotes: First draft.\nWill \
					 improve it later.\nlanguage: Go",
				),
			},
			Commit {
				id:      String::from("abc003"),
				message: String::from("fix: a bug fix"),
			},
			Commit {
				id:      String::from("abc004"),
				message: String::from(
					"algo(2025-09-09): Binary Search\nnotes: Recursive \
					 solution.\nlanguage: Python",
				),
			},
            Commit {
				id:      String::from("abc005"),
				message: String::from(
					"algo(2025-09-10): Bubble Sort\nnotes: Simple but inefficient.\nlanguage: C++\nRef.: https://example.com/bubble-sort",
				),
			},
            Commit {
				id:      String::from("abc006"),
				message: String::from(
					"algo(2025-09-11): Quick Sort\nnotes: A more complex solution.\nRef.: https://example.com/quick-sort\nlanguage: Rust",
				),
			},
            Commit {
				id:      String::from("abc007"),
				message: String::from(
					"algo(2025-09-12): Coursera Assignment\nnotes: First assignment of Coursera's Algorithms Part 1 course. Ref.: https://www.coursera.org/learn/algorithms-part1\nlanguage: Java",
				),
			},
		];

		let git_repo = GitRepository {
			root_path:    PathBuf::new(),
			display_path: "owner/repo".to_string(),
		};
		let analysis_files = AnalysisFiles::new(&git_repo, &[]).unwrap();
		let result = parse_commits(&commits, "owner/repo", &analysis_files).unwrap();

		assert_eq!(result.len(), 5);

		// Check 2025-09-08
		let day1_commits = result.get("2025-09-08").unwrap();
		assert_eq!(day1_commits.len(), 2);
		// Assert order for 2025-09-08
		assert_eq!(day1_commits[0].title, "Two Sum");
		assert_eq!(day1_commits[0].language, "Rust");
		assert_eq!(day1_commits[0].type_of, "algo");
		assert_eq!(day1_commits[0].notes, "Solved with hash map.");
		assert_eq!(
			day1_commits[0].link,
			"https://github.com/owner/repo/commit/abc001"
		);
		assert_eq!(day1_commits[0].reference, None);

		assert_eq!(day1_commits[1].title, "Web Crawler");
		assert_eq!(day1_commits[1].language, "Go");
		assert_eq!(day1_commits[1].type_of, "sysdes");
		assert_eq!(
			day1_commits[1].notes,
			"First draft.\nWill improve it later."
		);
		assert_eq!(
			day1_commits[1].link,
			"https://github.com/owner/repo/commit/abc002"
		);
		assert_eq!(day1_commits[1].reference, None);

		// Check 2025-09-09
		let day2_commits = result.get("2025-09-09").unwrap();
		assert_eq!(day2_commits.len(), 1);
		assert_eq!(day2_commits[0].title, "Binary Search");
		assert_eq!(day2_commits[0].language, "Python");
		assert_eq!(day2_commits[0].type_of, "algo");
		assert_eq!(day2_commits[0].notes, "Recursive solution.");
		assert_eq!(
			day2_commits[0].link,
			"https://github.com/owner/repo/commit/abc004"
		);
		assert_eq!(day2_commits[0].reference, None);

		// Check 2025-09-10
		let day3_commits = result.get("2025-09-10").unwrap();
		assert_eq!(day3_commits.len(), 1);
		assert_eq!(day3_commits[0].title, "Bubble Sort");
		assert_eq!(day3_commits[0].language, "C++");
		assert_eq!(day3_commits[0].type_of, "algo");
		assert_eq!(day3_commits[0].notes, "Simple but inefficient.");
		assert_eq!(
			day3_commits[0].link,
			"https://github.com/owner/repo/commit/abc005"
		);
		assert_eq!(
			day3_commits[0].reference,
			Some("https://example.com/bubble-sort".to_string())
		);

		// Check 2025-09-11
		let day4_commits = result.get("2025-09-11").unwrap();
		assert_eq!(day4_commits.len(), 1);
		assert_eq!(day4_commits[0].title, "Quick Sort");
		assert_eq!(day4_commits[0].language, "Rust");
		assert_eq!(day4_commits[0].type_of, "algo");
		assert_eq!(day4_commits[0].notes, "A more complex solution.");
		assert_eq!(
			day4_commits[0].link,
			"https://github.com/owner/repo/commit/abc006"
		);
		assert_eq!(
			day4_commits[0].reference,
			Some("https://example.com/quick-sort".to_string())
		);

		// Check 2025-09-12
		let day5_commits = result.get("2025-09-12").unwrap();
		assert_eq!(day5_commits.len(), 1);
		assert_eq!(day5_commits[0].title, "Coursera Assignment");
		assert_eq!(day5_commits[0].language, "Java");
		assert_eq!(day5_commits[0].type_of, "algo");
		assert_eq!(
			day5_commits[0].notes,
			"First assignment of Coursera's Algorithms Part 1 course."
		);
		assert_eq!(
			day5_commits[0].link,
			"https://github.com/owner/repo/commit/abc007"
		);
		assert_eq!(
			day5_commits[0].reference,
			Some("https://www.coursera.org/learn/algorithms-part1".to_string())
		);
	}

	#[test]
	fn test_parse_commits_with_analysis_file()
	-> Result<(), Box<dyn std::error::Error>> {
		// Arrange
		let commit_id = "abc1234def5678";
		let commits = vec![Commit {
			id:      commit_id.to_string(),
			message: String::from(
				"algo(2025-09-15): Test with Analysis\nlanguage: Rust",
			),
		}];

		let analysis_dir = tempdir()?;
		let analysis_file_name =
			format!("0001-test-with-analysis-{}.md", &commit_id[0..7]);
		let analysis_file_path = analysis_dir.path().join(&analysis_file_name);
		fs::write(&analysis_file_path, "## Test Analysis")?;

		let git_repo = GitRepository {
			root_path:    analysis_dir.path().to_path_buf(),
			display_path: "owner/repo".to_string(),
		};
		let analysis_files = AnalysisFiles::new(&git_repo, &[PathBuf::from("")])?;

		// Act
		let result = parse_commits(&commits, "owner/repo", &analysis_files)?;

		// Assert
		let day_commits = result.get("2025-09-15").unwrap();
		assert_eq!(day_commits.len(), 1);
		assert_eq!(
			day_commits[0].analysis,
			Some(analysis_file_name.to_string())
		);

		Ok(())
	}
}
