use crate::domain::git::entities::Commit;

pub trait CommitProvider {
	type Error;
	fn fetch(&self) -> Result<Vec<Commit>, Self::Error>;
	fn get_repo_path(&self) -> Result<String, Box<dyn std::error::Error>>;
}
