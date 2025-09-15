use serde::{Deserialize, Serialize};
use std::collections::HashMap;

#[derive(Serialize, Deserialize, Debug, Clone)]
pub struct CommitData {
    pub title: String,
    pub notes: String,
    pub link: String,
    pub language: String,
    pub type_of: String,
}

#[derive(Debug, Clone)]
pub struct Commit {
    pub message: String,
}

pub trait CommitProvider {
    type Error;
    fn fetch(&self) -> Result<Vec<Commit>, Self::Error>;
}

pub trait OutputWriter {
    type Error;
    fn write(&self, data: &HashMap<String, Vec<CommitData>>) -> Result<(), Self::Error>;
}
