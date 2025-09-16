use std::collections::HashMap;

use crate::domain::commit::{Commit, CommitData};

pub trait CommitProvider {
	type Error;
	fn fetch(&self) -> Result<Vec<Commit>, Self::Error>;
	fn get_repo_path(&self) -> Result<String, Box<dyn std::error::Error>>;
}

pub trait OutputWriter {
	type Error;
	fn write(
		&self,
		data: &HashMap<String, Vec<CommitData>>,
	) -> Result<(), Self::Error>;
}
