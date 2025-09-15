use git2::{Repository, Signature};
use kaizen::adapters::git_provider::GitCommitProvider;
use kaizen::domain::ports::CommitProvider;
use tempfile::tempdir;

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
fn test_git_commit_provider() {
    let dir = tempdir().unwrap();
    let path = dir.path();
    let repo = Repository::init(path).unwrap();

    // Create a few commits
    let c1_oid = create_commit(&repo, "feat: initial commit", None);
    let c1 = repo.find_commit(c1_oid).unwrap();
    create_commit(&repo, "algo(2025-01-01): Test Algo\nnotes: note1\nlanguage: Rust", Some(&c1));

    let provider = GitCommitProvider {
        url: path.to_str().unwrap().to_string(),
    };

    let commits = provider.fetch().unwrap();

    assert_eq!(commits.len(), 2);
    assert_eq!(commits[0].message, "algo(2025-01-01): Test Algo\nnotes: note1\nlanguage: Rust");
    assert_eq!(commits[1].message, "feat: initial commit");
}
