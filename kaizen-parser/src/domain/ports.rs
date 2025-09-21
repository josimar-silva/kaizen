use crate::domain::commit::Commit;
use crate::domain::kaizen::KaizenData;

pub trait CommitProvider {
	type Error;
	fn fetch(&self) -> Result<Vec<Commit>, Self::Error>;
	fn get_repo_path(&self) -> Result<String, Box<dyn std::error::Error>>;
}

pub trait OutputWriter {
	type Error;
	fn write(&self, data: &KaizenData) -> Result<(), Self::Error>;
}
