use std::collections::HashMap;

use rayon::prelude::*;
use regex::Regex;

use crate::domain::commit::{Commit, CommitData, CommitsByDate};

pub fn parse_commits(
	commits: &[Commit],
	repo_path: &str,
) -> Result<CommitsByDate, regex::Error> {
	let commit_re = Regex::new(
		r"^(?P<type>algo|sysdes)\((?P<date>\d{4}-\d{2}-\d{2})\): (?P<title>.*)",
	)?;
	let notes_re = Regex::new(r"(?ms)^notes: (?P<notes>.*?)(?:^Ref\.:|^\w+:|\z)")?;
	let lang_re = Regex::new(r"(?m)^language: (?P<language>.*)")?;
	let ref_re = Regex::new(r"(?m)^Ref\.: (?P<reference>.*)")?;

	let commits_by_date: CommitsByDate = commits
		.par_iter()
		.filter_map(|commit| {
			commit_re.captures(&commit.message).map(|commit_caps| {
				let date = commit_caps["date"].to_string();
				let title = commit_caps["title"].to_string();
				let type_of = commit_caps["type"].to_string();
				let notes = notes_re
					.captures(&commit.message)
					.map(|c| c["notes"].trim().to_string())
					.unwrap_or_default();
				let language = lang_re
					.captures(&commit.message)
					.map(|c| c["language"].to_string())
					.unwrap_or_default();
				let reference = ref_re
					.captures(&commit.message)
					.map(|c| c["reference"].trim().to_string());
				let link =
					format!("https://github.com/{}/commit/{}", repo_path, commit.id);
				(date, CommitData {
					title,
					notes,
					link,
					language,
					type_of,
					reference,
				})
			})
		})
		.fold(HashMap::new, |mut acc: CommitsByDate, (date, data)| {
			acc.entry(date).or_default().push(data);
			acc
		})
		.reduce(CommitsByDate::new, |mut a, b| {
			for (k, v) in b {
				a.entry(k).or_default().extend(v);
			}
			a
		});

	Ok(commits_by_date)
}

#[cfg(test)]
mod tests {
	use super::*;
	use crate::domain::commit::Commit;

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
		];

		let result = parse_commits(&commits, "owner/repo").unwrap();

		assert_eq!(result.len(), 4);

		// Check 2025-09-08
		let day1_commits = result.get("2025-09-08").unwrap();
		assert_eq!(day1_commits.len(), 2);
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
	}
}
