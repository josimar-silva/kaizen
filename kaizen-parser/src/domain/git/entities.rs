use std::collections::HashMap;

use serde::{Deserialize, Serialize};

#[derive(Serialize, Deserialize, Debug, Clone, PartialEq)]
pub struct CommitData {
	pub title:     String,
	pub notes:     String,
	pub link:      String,
	pub language:  String,
	pub type_of:   String,
	#[serde(skip_serializing_if = "Option::is_none")]
	pub reference: Option<String>,
	#[serde(skip_serializing_if = "Option::is_none")]
	pub analysis:  Option<String>,
}

#[derive(Debug, Clone)]
pub struct Commit {
	pub id:      String,
	pub message: String,
}

pub type CommitsByDate = HashMap<String, Vec<CommitData>>;
