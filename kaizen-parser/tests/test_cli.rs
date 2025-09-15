use assert_cmd::prelude::*;
use git2::{Repository, Signature};
use predicates::prelude::*;
use std::fs;
use std::process::Command;
use tempfile::tempdir;

// Helper function from the other integration test
fn create_commit(repo: &Repository, message: &str, parent: Option<&git2::Commit>) -> git2::Oid {
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
fn test_cli_e2e() -> Result<(), Box<dyn std::error::Error>> {
    // 1. Setup a temporary git repository
    let repo_dir = tempdir()?;
    let repo = Repository::init(repo_dir.path())?;
    let c1_oid = create_commit(&repo, "feat: initial commit", None);
    let c1 = repo.find_commit(c1_oid)?;
    create_commit(&repo, "algo(2025-09-15): E2E Test Algo\nnotes: A test note.\nlanguage: Rust", Some(&c1));

    // 2. Setup output file path
    let output_dir = tempdir()?;
    let output_file = output_dir.path().join("data.json");

    // 3. Run the CLI command
    let mut cmd = Command::cargo_bin("parser")?;
    cmd.arg("parse")
        .arg("--repository")
        .arg(repo_dir.path().to_str().unwrap())
        .arg("--output")
        .arg(&output_file);

    // 4. Assert command success
    cmd.assert().success().stdout(predicate::str::contains("Repository parsed successfully!"));

    // 5. Assert file content
    let content = fs::read_to_string(output_file)?;
    assert!(content.contains("E2E Test Algo"));
    assert!(content.contains("2025-09-15"));

    Ok(())
}
