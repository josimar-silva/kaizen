use std::collections::HashMap;
use std::{fs, io};

use kaizen::adapters::json_writer::JsonFileOutputWriter;
use kaizen::domain::commit::CommitData;
use kaizen::domain::ports::OutputWriter;
use tempfile::tempdir;

#[test]
fn test_json_file_output_writer() -> Result<(), io::Error> {
	let dir = tempdir()?;
	let file_path = dir.path().join("data.json");

	let writer = JsonFileOutputWriter {
		path: file_path.clone(),
	};

	let mut data: HashMap<String, Vec<CommitData>> = HashMap::new();
	data.insert("2025-01-01".to_string(), vec![CommitData {
		title:    "Test Title".to_string(),
		notes:    "Test notes.".to_string(),
		link:     "http://example.com".to_string(),
		language: "Rust".to_string(),
		type_of:  "algo".to_string(),
	}]);

	writer.write(&data).unwrap();

	let content = fs::read_to_string(file_path)?;
	let expected_content = serde_json::to_string_pretty(&data).unwrap();

	assert_eq!(content, expected_content);

	dir.close()?;
	Ok(())
}
