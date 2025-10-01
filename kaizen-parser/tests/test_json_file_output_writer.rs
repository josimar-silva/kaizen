use std::collections::HashMap;
use std::{fs, io};

use kaizen::adapters::output::json::JsonFileOutputWriter;
use kaizen::domain::git::entities::{CommitData, CommitsByDate};
use kaizen::domain::kaizen::entities::{KaizenData, KaizenStats};
use kaizen::domain::kaizen::ports::OutputWriter;
use ordermap::OrderMap;
use tempfile::tempdir;

#[test]
fn test_json_file_output_writer() -> Result<(), io::Error> {
	let dir = tempdir()?;
	let file_path = dir.path().join("data.json");

	let writer = JsonFileOutputWriter {
		path: file_path.clone(),
	};

	let mut commits: CommitsByDate = OrderMap::new();
	commits.insert("2025-01-01".to_string(), vec![CommitData {
		title:     "Test Title".to_string(),
		notes:     "Test notes.".to_string(),
		link:      "http://example.com".to_string(),
		language:  "Rust".to_string(),
		type_of:   "algo".to_string(),
		reference: None,
		analysis:  None,
		source:    None,
	}]);

	let mut stats = KaizenStats {
		total_algorithms:      1,
		total_days:            1,
		current_streak:        1,
		longest_streak:        1,
		language_distribution: HashMap::new(),
		monthly_activity:      HashMap::new(),
	};
	stats.language_distribution.insert("Rust".to_string(), 1);
	stats.monthly_activity.insert("2025-01".to_string(), 1);

	let kaizen_data = KaizenData {
		stats,
		commits: commits.clone(),
	};

	writer.write(&kaizen_data).unwrap();

	let content = fs::read_to_string(file_path)?;
	let expected_content = serde_json::to_string_pretty(&kaizen_data).unwrap();

	assert_eq!(content, expected_content);

	dir.close()?;
	Ok(())
}
