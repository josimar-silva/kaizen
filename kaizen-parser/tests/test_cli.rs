use std::fs;

use git2::{Repository, Signature};
use kaizen::domain::kaizen::KaizenData;
use kaizen::{Cli, Command, ParseArgs, run};
use tempfile::tempdir;

// Helper function from the other integration test
fn create_commit(
	repo: &Repository,
	message: &str,
	parent: Option<&git2::Commit>,
) -> git2::Oid {
	let tree_id = repo.treebuilder(None).unwrap().write().unwrap();
	let tree = repo.find_tree(tree_id).unwrap();
	let signature = Signature::now("Test User", "test@example.com").unwrap();
	let parents = parent.map_or(vec![], |p| vec![p]);
	repo.commit(
		Some("HEAD"),
		&signature,
		&signature,
		message,
		&tree,
		&parents,
	)
	.unwrap()
}

#[test]
fn test_cli_parse_command_commits() -> Result<(), Box<dyn std::error::Error>> {
	// Arrange
	let repo_dir = tempdir()?;
	let repo = Repository::init(repo_dir.path())?;
	let c1_oid = create_commit(&repo, "feat: initial commit", None);
	let c1 = repo.find_commit(c1_oid)?;
	create_commit(
		&repo,
		"algo(2025-09-15): E2E Test Algo\nnotes: A test note.\nlanguage: Rust",
		Some(&c1),
	);

	let output_dir = tempdir()?;
	let output_file = output_dir.path().join("data.json");

	// Act
	let cli = Cli {
		command: Command::Parse(ParseArgs {
			repository: repo_dir.path().to_str().unwrap().to_string(),
			output:     output_file.clone(),
		}),
	};
	run(cli)?;

	let content = fs::read_to_string(output_file)?;
	let kaizen_data: KaizenData = serde_json::from_str(&content)?;

	// Assert
	assert!(kaizen_data.commits.contains_key("2025-09-15"));
	let day_commits = kaizen_data.commits.get("2025-09-15").unwrap();
	assert_eq!(day_commits.len(), 1);
	assert_eq!(day_commits[0].title, "E2E Test Algo");
	assert_eq!(day_commits[0].notes, "A test note.");
	assert_eq!(day_commits[0].language, "Rust");

	Ok(())
}

#[test]
fn test_cli_parse_command_stats() -> Result<(), Box<dyn std::error::Error>> {
	// Arrange
	let repo_dir = tempdir()?;
	let repo = Repository::init(repo_dir.path())?;

	let c1_oid =
		create_commit(&repo, "algo(2025-08-10): Aug Algo\nlanguage: Rust", None);
	let c1 = repo.find_commit(c1_oid)?;

	let c2_oid = create_commit(
		&repo,
		"algo(2025-09-12): Sep Algo 1\nlanguage: Go",
		Some(&c1),
	);
	let c2 = repo.find_commit(c2_oid)?;

	let c3_oid = create_commit(
		&repo,
		"algo(2025-09-13): Sep Algo 2\nlanguage: Rust",
		Some(&c2),
	);
	let c3 = repo.find_commit(c3_oid)?;

	let c4_oid = create_commit(
		&repo,
		"algo(2025-09-13): Sep Algo 3\nlanguage: Python",
		Some(&c3),
	);
	let c4 = repo.find_commit(c4_oid)?;

	create_commit(
		&repo,
		"algo(2025-09-14): Sep Algo 4\nlanguage: Rust",
		Some(&c4),
	);

	let output_dir = tempdir()?;
	let output_file = output_dir.path().join("data.json");

	// Act
	let cli = Cli {
		command: Command::Parse(ParseArgs {
			repository: repo_dir.path().to_str().unwrap().to_string(),
			output:     output_file.clone(),
		}),
	};
	run(cli)?;

	let content = fs::read_to_string(output_file)?;
	let kaizen_data: KaizenData = serde_json::from_str(&content)?;

	// Assert
	assert_eq!(kaizen_data.stats.total_algorithms, 5);
	assert_eq!(kaizen_data.stats.total_days, 4);
	assert_eq!(kaizen_data.stats.longest_streak, 3);

	assert_eq!(
		kaizen_data.stats.language_distribution.get("Rust"),
		Some(&3)
	);
	assert_eq!(kaizen_data.stats.language_distribution.get("Go"), Some(&1));
	assert_eq!(
		kaizen_data.stats.language_distribution.get("Python"),
		Some(&1)
	);

	assert_eq!(kaizen_data.stats.monthly_activity.get("2025-08"), Some(&1));
	assert_eq!(kaizen_data.stats.monthly_activity.get("2025-09"), Some(&4));

	Ok(())
}
