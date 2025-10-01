use git2::{Repository, Sort};
use tempfile::Builder;
use url::Url;

use crate::domain::git::entities::Commit;
use crate::domain::git::ports::CommitProvider;

pub struct GitCommitProvider {
	pub url: String,
}

impl CommitProvider for GitCommitProvider {
	type Error = git2::Error;

	fn fetch(&self) -> Result<Vec<Commit>, Self::Error> {
		let temp_dir = Builder::new().prefix("kaizen-parser-").tempdir().unwrap();
		let repo = Repository::clone(&self.url, temp_dir.path())?;

		let mut revwalk = repo.revwalk()?;
		revwalk.push_head()?;
		revwalk.set_sorting(Sort::TIME)?;

		let mut commits = Vec::new();
		for oid in revwalk {
			let oid = oid?;
			let commit = repo.find_commit(oid)?;
			let message = commit.message().unwrap_or("").to_string();
			commits.push(Commit {
				id: oid.to_string(),
				message,
			});
		}

		Ok(commits)
	}

	fn get_repo_path(&self) -> Result<String, Box<dyn std::error::Error>> {
		if self.url.starts_with("http") {
			let url = Url::parse(&self.url)?;
			Ok(url
				.path()
				.trim_start_matches('/')
				.trim_end_matches(".git")
				.to_string())
		} else {
			Ok(self.url.clone())
		}
	}
}

#[cfg(test)]
mod tests {
	use super::*;

	#[test]
	fn test_get_repo_path_with_http_url() {
		let provider = GitCommitProvider {
			url: "https://github.com/owner/repo.git".to_string(),
		};
		let repo_path = provider.get_repo_path().unwrap();
		assert_eq!(repo_path, "owner/repo");
	}

	#[test]
	fn test_get_repo_path_with_local_path() {
		let provider = GitCommitProvider {
			url: "/path/to/local/repo".to_string(),
		};
		let repo_path = provider.get_repo_path().unwrap();
		assert_eq!(repo_path, "/path/to/local/repo");
	}

	#[test]
	fn test_get_repo_path_with_http_url_no_git_suffix() {
		let provider = GitCommitProvider {
			url: "https://github.com/owner/repo".to_string(),
		};
		let repo_path = provider.get_repo_path().unwrap();
		assert_eq!(repo_path, "owner/repo");
	}
}
