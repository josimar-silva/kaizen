use std::collections::HashMap;

use serde::{Deserialize, Serialize};

use crate::domain::git::entities::CommitsByDate;

#[derive(Serialize, Deserialize, Debug, PartialEq)]
pub struct KaizenStats {
	#[serde(rename = "totalAlgorithms")]
	pub total_algorithms:      usize,
	#[serde(rename = "totalDays")]
	pub total_days:            usize,
	#[serde(rename = "currentStreak")]
	pub current_streak:        i64,
	#[serde(rename = "longestStreak")]
	pub longest_streak:        i64,
	#[serde(rename = "languageDistribution")]
	pub language_distribution: HashMap<String, usize>,
	#[serde(rename = "monthlyActivity")]
	pub monthly_activity:      HashMap<String, usize>,
	#[serde(rename = "sourceDistribution")]
	pub source_distribution:   HashMap<String, usize>,
	#[serde(rename = "mostActiveDay")]
	pub most_active_day:       Option<String>,
}

#[derive(Serialize, Deserialize, Debug, PartialEq)]
pub struct KaizenData {
	pub stats:   KaizenStats,
	pub commits: CommitsByDate,
}
