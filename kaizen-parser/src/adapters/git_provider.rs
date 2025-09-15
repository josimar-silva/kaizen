use git2::{Repository, Sort};
use tempfile::Builder;

use crate::domain::commit::Commit;
use crate::domain::ports::CommitProvider;

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
}
