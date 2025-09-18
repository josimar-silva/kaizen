use std::collections::HashMap;

use chrono::NaiveDate;
use serde::{Deserialize, Serialize};

#[derive(Serialize, Deserialize, Debug, Clone, PartialEq)]
pub struct CommitData {
    pub title: String,
    pub notes: String,
    pub link: String,
    pub language: String,
    #[serde(rename = "typeOf")]
    pub type_of: String,
}

pub type KaizenData = HashMap<String, Vec<CommitData>>;

#[derive(Serialize, Deserialize, Debug, PartialEq)]
pub struct KaizenStats {
    #[serde(rename = "totalAlgorithms")]
    pub total_algorithms: usize,
    #[serde(rename = "totalDays")]
    pub total_days: usize,
    #[serde(rename = "currentStreak")]
    pub current_streak: i64,
    #[serde(rename = "longestStreak")]
    pub longest_streak: i64,
    #[serde(rename = "languageDistribution")]
    pub language_distribution: HashMap<String, usize>,
    #[serde(rename = "monthlyActivity")]
    pub monthly_activity: HashMap<String, usize>,
}

pub fn calculate_stats(data: &KaizenData) -> KaizenStats {
    let entries: Vec<_> = data.iter().collect();
    let total_days = entries.len();
    let total_algorithms = entries
        .iter()
        .map(|(_, algorithms)| algorithms.len())
        .sum();

    let mut language_distribution: HashMap<String, usize> = HashMap::new();
    for (_, algorithms) in &entries {
        for algorithm in *algorithms {
            *language_distribution
                .entry(algorithm.language.clone())
                .or_insert(0) += 1;
        }
    }

    let mut monthly_activity: HashMap<String, usize> = HashMap::new();
    for (date, algorithms) in &entries {
        let month = date[..7].to_string();
        *monthly_activity.entry(month).or_insert(0) += algorithms.len();
    }

    let current_streak = calculate_current_streak(data);
    let longest_streak = calculate_longest_streak(data);

    KaizenStats {
        total_algorithms,
        total_days,
        current_streak,
        longest_streak,
        language_distribution,
        monthly_activity,
    }
}

fn calculate_current_streak(data: &KaizenData) -> i64 {
    let mut streak = 0;
    let mut today = chrono::Local::now().naive_local().date();

    for _ in 0..365 {
        let date_string = today.format("%Y-%m-%d").to_string();
        if data.contains_key(&date_string) {
            streak += 1;
        } else {
            break;
        }
        today = today.pred_opt().unwrap();
    }

    streak
}

fn calculate_longest_streak(data: &KaizenData) -> i64 {
    let mut dates: Vec<_> = data.keys().collect();
    dates.sort();

    let mut max_streak = 0;
    let mut current_streak = 0;
    let mut last_date: Option<NaiveDate> = None;

    for date_string in dates {
        let date = NaiveDate::parse_from_str(date_string, "%Y-%m-%d").unwrap();
        if let Some(last) = last_date {
            if date.signed_duration_since(last).num_days() == 1 {
                current_streak += 1;
            } else {
                current_streak = 1;
            }
        } else {
            current_streak = 1;
        }
        max_streak = max_streak.max(current_streak);
        last_date = Some(date);
    }

    max_streak
}
