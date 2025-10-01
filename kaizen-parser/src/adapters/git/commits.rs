use git2::{Repository, Sort};

use crate::domain::git::entities::{Commit, GitRepository};
use crate::domain::git::ports::CommitProvider;

pub struct GitCommitProvider;

impl CommitProvider for GitCommitProvider {
	type Error = git2::Error;

	fn fetch(&self, repository: &GitRepository) -> Result<Vec<Commit>, Self::Error> {
		let repo = Repository::open(&repository.root_path)?;

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

#[cfg(test)]
mod tests {
	use git2::Signature;
	use tempfile::tempdir;

	use super::*;
	use crate::domain::git::entities::GitRepository;

	fn create_commit(
		repo: &Repository,
		message: &str,
		parent: Option<&git2::Commit>,
	) -> git2::Oid {
		let tree_id = repo.treebuilder(None).unwrap().write().unwrap();
		let tree = repo.find_tree(tree_id).unwrap();
		let signature = Signature::now("Test User", "test@example.com").unwrap();
		let parents = parent.map_or(vec![], |p| vec![p]);
		repo.commit(
			Some("HEAD"),
			&signature,
			&signature,
			message,
			&tree,
			&parents,
		)
		.unwrap()
	}

	#[test]
	fn test_fetch_commits_from_local_repo() {
		// Arrange
		let dir = tempdir().unwrap();
		let path = dir.path();
		let repo = Repository::init(path).unwrap();

		let c1_oid = create_commit(&repo, "feat: initial commit", None);
		let c1 = repo.find_commit(c1_oid).unwrap();
		create_commit(
			&repo,
			"algo(2025-01-01): Test Algo\nnotes: note1\nlanguage: Rust",
			Some(&c1),
		);

		let git_repository = GitRepository {
			root_path:    path.to_path_buf(),
			display_path: "local/repo".to_string(),
		};

		let provider = GitCommitProvider;

		// Act
		let commits = provider.fetch(&git_repository).unwrap();

		// Assert
		assert_eq!(commits.len(), 2);
		assert_eq!(
			commits[0].message,
			"algo(2025-01-01): Test Algo\nnotes: note1\nlanguage: Rust"
		);
		assert_eq!(commits[1].message, "feat: initial commit");
	}
}
