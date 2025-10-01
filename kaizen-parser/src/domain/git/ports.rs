use crate::domain::git::entities::{Commit, GitRepository};

pub trait CommitProvider {
	type Error;
	fn fetch(&self, repository: &GitRepository) -> Result<Vec<Commit>, Self::Error>;
}
