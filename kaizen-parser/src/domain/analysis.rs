use std::collections::HashMap;
use std::path::{Path, PathBuf};

pub struct AnalysisFiles {
	files_by_hash: HashMap<String, String>,
}

impl AnalysisFiles {
	pub fn new(analysis_dirs: &[PathBuf]) -> Result<Self, std::io::Error> {
		let mut files_by_hash = HashMap::new();

		for dir in analysis_dirs {
			if !dir.exists() {
				continue;
			}

			let files = std::fs::read_dir(dir)?
				.filter_map(|entry| entry.ok().map(|e| e.path()))
				.filter(|p| {
					p.is_file() &&
						p.extension().and_then(|s| s.to_str()) == Some("md")
				});

			for file_path in files {
				if let Some(hash) = Self::extract_hash_from_filename(&file_path) {
					files_by_hash
						.insert(hash, file_path.to_string_lossy().into_owned());
				}
			}
		}

		Ok(Self { files_by_hash })
	}

	pub fn get(&self, commit_hash: &str) -> Option<&String> {
		if commit_hash.is_empty() {
			return None;
		}

		let short_hash = &commit_hash[0..std::cmp::min(commit_hash.len(), 7)];
		self.files_by_hash.get(short_hash)
	}

	/// Expects filenames like '0001-some-algo-abc1234.md' and extracts the
	/// trailing 7-char hex hash after the last '-'.
	fn extract_hash_from_filename(path: &Path) -> Option<String> {
		path.file_stem()
			.and_then(|stem| stem.to_str())
			.and_then(|stem_str| stem_str.rsplit('-').next())
			.filter(|hash| {
				hash.len() == 7 && hash.chars().all(|c| c.is_ascii_hexdigit())
			})
			.map(|s| s.to_string())
	}
}

#[cfg(test)]
mod tests {
	use std::fs;

	use tempfile::tempdir;

	use super::*;

	#[test]
	fn test_analysis_files_new() -> Result<(), std::io::Error> {
		// Arrange
		let dir = tempdir()?;
		let file_path = dir.path().join("0001-some-algo-abc1234.md");
		fs::write(&file_path, "test content")?;

		let dirs = vec![dir.path().to_path_buf()];

		// Act
		let analysis_files = AnalysisFiles::new(&dirs)?;

		// Assert
		assert_eq!(analysis_files.files_by_hash.len(), 1);
		assert_eq!(
			analysis_files.files_by_hash.get("abc1234"),
			Some(&file_path.to_string_lossy().to_string())
		);

		Ok(())
	}

	#[test]
	fn test_analysis_files_get() {
		let mut files_by_hash = HashMap::new();
		files_by_hash.insert(
			"abc1234".to_string(),
			"/path/to/0001-some-algo-abc1234.md".to_string(),
		);
		let analysis_files = AnalysisFiles { files_by_hash };

		assert_eq!(
			analysis_files.get("abc1234567890"),
			Some(&"/path/to/0001-some-algo-abc1234.md".to_string())
		);
		assert_eq!(analysis_files.get("xyz0987654321"), None);
	}

	#[test]
	fn test_analysis_files_get_empty_hash() {
		let files_by_hash = HashMap::new();
		let analysis_files = AnalysisFiles { files_by_hash };
		assert_eq!(analysis_files.get(""), None);
	}

	#[test]
	fn test_extract_hash_from_filename() {
		let path = Path::new("/path/to/0001-some-algo-abc1234.md");
		assert_eq!(
			AnalysisFiles::extract_hash_from_filename(path),
			Some("abc1234".to_string())
		);

		let path_no_hash = Path::new("/path/to/file.md");
		assert_eq!(
			AnalysisFiles::extract_hash_from_filename(path_no_hash),
			None
		);

		let path_short_hash = Path::new("/path/to/file-123.md");
		assert_eq!(
			AnalysisFiles::extract_hash_from_filename(path_short_hash),
			None
		);

		let path_non_hex = Path::new("/path/to/file-xyz123g.md");
		assert_eq!(
			AnalysisFiles::extract_hash_from_filename(path_non_hex),
			None
		);
	}

	#[test]
	fn test_analysis_files_new_filters_non_md_files() -> Result<(), std::io::Error> {
		// Arrange
		let dir = tempdir()?;
		let md_file_path = dir.path().join("0001-some-algo-abc1234.md");
		let txt_file_path = dir.path().join("0002-another-thing-xyz5678.txt");
		fs::write(&md_file_path, "md content")?;
		fs::write(&txt_file_path, "txt content")?;

		let dirs = vec![dir.path().to_path_buf()];

		// Act
		let analysis_files = AnalysisFiles::new(&dirs)?;

		// Assert
		assert_eq!(analysis_files.files_by_hash.len(), 1);
		assert!(analysis_files.files_by_hash.contains_key("abc1234"));
		assert!(!analysis_files.files_by_hash.contains_key("xyz5678"));

		Ok(())
	}
}
