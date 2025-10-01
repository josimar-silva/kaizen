use git2::{Repository, Signature};
use kaizen::adapters::git::repository::Git2RepositoryProvider;
use tempfile::tempdir;

#[test]
fn test_provider_from_local_path() -> Result<(), Box<dyn std::error::Error>> {
	// Arrange
	let repo_dir = tempdir()?;
	let repo_path = repo_dir.path();
	Repository::init(repo_path)?;

	// Act
	let provider = Git2RepositoryProvider;
	let repo_handle = provider.provide(repo_path.to_str().unwrap())?;
	let git_repo = repo_handle.repository();

	// Assert
	assert_eq!(git_repo.root_path, repo_path);
	assert_eq!(git_repo.display_path, repo_path.to_str().unwrap());
	assert!(repo_handle.temp_dir().is_none());

	Ok(())
}

#[test]
fn test_provider_from_remote_url() -> Result<(), Box<dyn std::error::Error>> {
	// Arrange: Create a "remote" bare repository
	let remote_repo_dir = tempdir()?;
	let remote_path = remote_repo_dir.path();
	let remote_repo = Repository::init_bare(remote_path)?;

	// Add a commit to the remote
	let signature = Signature::now("Test", "test@example.com")?;
	let tree_id = remote_repo.treebuilder(None)?.write()?;
	let tree = remote_repo.find_tree(tree_id)?;
	remote_repo.commit(
		Some("HEAD"),
		&signature,
		&signature,
		"Initial",
		&tree,
		&[],
	)?;

	let remote_url = format!("file://{}", remote_path.display());

	// Act
	let provider = Git2RepositoryProvider;
	let repo_handle = provider.provide(&remote_url)?;
	let git_repo = repo_handle.repository();

	// Assert
	// 1. The root_path should be a new temporary directory, not the original remote
	//    path
	assert_ne!(git_repo.root_path, remote_path);
	assert!(git_repo.root_path.exists());
	assert!(git_repo.root_path.join(".git").exists());

	// 2. The display_path should be the parsed owner/repo from the URL
	let expected_display_path =
		remote_path.to_str().unwrap().trim_start_matches('/');
	assert_eq!(git_repo.display_path, expected_display_path);

	// 3. The handle should contain the TempDir guard
	assert!(repo_handle.temp_dir().is_some());

	Ok(())
}
