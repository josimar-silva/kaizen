use git2::Repository;
use tempfile::{Builder, TempDir};
use url::Url;

use crate::domain::git::entities::GitRepository;

pub struct Git2RepositoryProvider;

#[derive(Debug)]
pub struct RepositoryHandle {
	repository: GitRepository,
	_temp_dir:  Option<TempDir>,
}

impl RepositoryHandle {
	pub fn repository(&self) -> &GitRepository {
		&self.repository
	}

	pub fn temp_dir(&self) -> &Option<TempDir> {
		&self._temp_dir
	}
}

impl Git2RepositoryProvider {
	pub fn provide(&self, path: &str) -> Result<RepositoryHandle, git2::Error> {
		let (repo, temp_dir, display_path) =
			if path.starts_with("http") || path.starts_with("file") {
				let temp_dir =
					Builder::new().prefix("kaizen-parser-").tempdir().unwrap();
				let repo = Repository::clone(path, temp_dir.path())?;
				let display_path = Url::parse(path)
					.ok()
					.map(|url| {
						url.path()
							.trim_start_matches('/')
							.trim_end_matches(".git")
							.to_string()
					})
					.unwrap_or_else(|| path.to_string());
				(repo, Some(temp_dir), display_path)
			} else {
				let repo = Repository::open(path)?;
				(repo, None, path.to_string())
			};

		let root_path = repo.workdir().unwrap_or_else(|| repo.path()).to_path_buf();

		Ok(RepositoryHandle {
			repository: GitRepository {
				root_path,
				display_path,
			},
			_temp_dir:  temp_dir,
		})
	}
}
