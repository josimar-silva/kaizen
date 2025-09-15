use serde::{Deserialize, Serialize};

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
