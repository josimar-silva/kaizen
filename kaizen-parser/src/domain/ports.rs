use std::collections::HashMap;

use crate::domain::commit::{Commit, CommitData};

pub trait CommitProvider {
	type Error;
	fn fetch(&self) -> Result<Vec<Commit>, Self::Error>;
}

pub trait OutputWriter {
	type Error;
	fn write(
		&self,
		data: &HashMap<String, Vec<CommitData>>,
	) -> Result<(), Self::Error>;
}
