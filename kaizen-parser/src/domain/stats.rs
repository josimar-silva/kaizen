use std::collections::HashMap;

use chrono::NaiveDate;

use crate::domain::commit::CommitsByDate;
use crate::domain::kaizen::KaizenStats;

pub fn calculate_stats(data: &CommitsByDate) -> KaizenStats {
	let entries: Vec<_> = data.iter().collect();
	let total_days = entries.len();
	let total_algorithms =
		entries.iter().map(|(_, algorithms)| algorithms.len()).sum();

	let mut language_distribution: HashMap<String, usize> = HashMap::new();
	for (_, algorithms) in &entries {
		for algorithm in algorithms.iter() {
			*language_distribution
				.entry(algorithm.language.clone())
				.or_insert(0) += 1;
		}
	}

	let mut monthly_activity: HashMap<String, usize> = HashMap::new();
	for (date, algorithms) in &entries {
		let month = date[..7].to_string();
		*monthly_activity.entry(month).or_insert(0) += algorithms.len();
	}

	let today = chrono::Local::now().naive_local().date();
	let current_streak = calculate_current_streak(data, today);
	let longest_streak = calculate_longest_streak(data);

	KaizenStats {
		total_algorithms,
		total_days,
		current_streak,
		longest_streak,
		language_distribution,
		monthly_activity,
	}
}

fn calculate_current_streak(commits: &CommitsByDate, today: NaiveDate) -> i64 {
	let mut streak = 0;
	let mut current_day = today;

	loop {
		let date_string = current_day.format("%Y-%m-%d").to_string();
		if commits.contains_key(&date_string) {
			streak += 1;
		} else {
			break;
		}
		if let Some(prev) = current_day.pred_opt() {
			current_day = prev;
		} else {
			break;
		}
	}

	streak
}

fn calculate_longest_streak(data: &CommitsByDate) -> i64 {
	let mut dates: Vec<_> = data.keys().collect();
	dates.sort();

	let mut max_streak = 0;
	let mut current_streak = 0;
	let mut last_date: Option<NaiveDate> = None;

	for date_string in dates {
		let date = NaiveDate::parse_from_str(date_string, "%Y-%m-%d").unwrap();
		if let Some(last) = last_date {
			if date.signed_duration_since(last).num_days() == 1 {
				current_streak += 1;
			} else {
				current_streak = 1;
			}
		} else {
			current_streak = 1;
		}
		max_streak = max_streak.max(current_streak);
		last_date = Some(date);
	}

	max_streak
}

#[cfg(test)]
mod tests {
	use std::collections::HashMap;

	use super::*;
	use crate::domain::commit::CommitData;

	fn create_commit_data(language: &str) -> CommitData {
		CommitData {
			title:     "Test".to_string(),
			notes:     "Test notes".to_string(),
			link:      "http://example.com".to_string(),
			language:  language.to_string(),
			type_of:   "algo".to_string(),
			reference: None,
		}
	}

	#[test]
	fn test_calculate_stats_basic() {
		let mut data: CommitsByDate = HashMap::new();
		data.insert("2025-09-20".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-21".to_string(), vec![
			create_commit_data("Go"),
			create_commit_data("Rust"),
		]);

		let stats = calculate_stats(&data);

		assert_eq!(stats.total_algorithms, 3);
		assert_eq!(stats.total_days, 2);
		assert_eq!(stats.language_distribution.get("Rust"), Some(&2));
		assert_eq!(stats.language_distribution.get("Go"), Some(&1));
		assert_eq!(stats.monthly_activity.get("2025-09"), Some(&3));
	}

	#[test]
	fn test_calculate_longest_streak() {
		let mut data: CommitsByDate = HashMap::new();
		data.insert("2025-09-18".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-19".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-21".to_string(), vec![create_commit_data("Rust")]); // break
		data.insert("2025-09-22".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-23".to_string(), vec![create_commit_data("Rust")]);

		let longest_streak = calculate_longest_streak(&data);
		assert_eq!(longest_streak, 3);
	}

	#[test]
	fn test_calculate_longest_streak_no_commits() {
		let data: CommitsByDate = HashMap::new();
		let longest_streak = calculate_longest_streak(&data);
		assert_eq!(longest_streak, 0);
	}

	#[test]
	fn test_calculate_longest_streak_single_day() {
		let mut data: CommitsByDate = HashMap::new();
		data.insert("2025-09-21".to_string(), vec![create_commit_data("Rust")]);
		let longest_streak = calculate_longest_streak(&data);
		assert_eq!(longest_streak, 1);
	}

	#[test]
	fn test_calculate_current_streak() {
		let mut data: CommitsByDate = HashMap::new();
		let today = NaiveDate::from_ymd_opt(2025, 9, 21).unwrap();

		// Streak of 3
		data.insert("2025-09-21".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-20".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-19".to_string(), vec![create_commit_data("Rust")]);
		// Break in streak
		data.insert("2025-09-17".to_string(), vec![create_commit_data("Rust")]);

		let current_streak = calculate_current_streak(&data, today);
		assert_eq!(current_streak, 3);
	}

	#[test]
	fn test_calculate_current_streak_no_commit_today() {
		let mut data: CommitsByDate = HashMap::new();
		let today = NaiveDate::from_ymd_opt(2025, 9, 21).unwrap();

		data.insert("2025-09-20".to_string(), vec![create_commit_data("Rust")]);
		data.insert("2025-09-19".to_string(), vec![create_commit_data("Rust")]);

		let current_streak = calculate_current_streak(&data, today);
		assert_eq!(current_streak, 0);
	}

	#[test]
	fn test_calculate_current_streak_no_commits() {
		let data: CommitsByDate = HashMap::new();
		let today = NaiveDate::from_ymd_opt(2025, 9, 21).unwrap();
		let current_streak = calculate_current_streak(&data, today);
		assert_eq!(current_streak, 0);
	}
}
