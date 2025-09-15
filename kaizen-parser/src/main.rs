use clap::{Parser, Subcommand};
use kaizen::adapters::git_provider::GitCommitProvider;
use kaizen::adapters::json_writer::JsonFileOutputWriter;
use kaizen::domain::parser::parse_commits;
use kaizen::domain::ports::{CommitProvider, OutputWriter};
use std::path::PathBuf;
use url::Url;

pub mod adapters;
pub mod domain;

#[derive(Parser)]
#[command(author, version, about, long_about = None)]
struct Cli {
    #[command(subcommand)]
    command: Command,
}

#[derive(Subcommand)]
enum Command {
    /// Parses a git repository and generates a JSON data file.
    Parse(ParseArgs),
}

#[derive(Parser)]
struct ParseArgs {
    /// The git repository URL to parse.
    #[arg(long)]
    repository: String,

    /// The output file path for the generated JSON.
    #[arg(long)]
    output: PathBuf,
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let cli = Cli::parse();

    match cli.command {
        Command::Parse(args) => {
            println!("Parsing repository: {}", args.repository);

            let commit_provider = GitCommitProvider { url: args.repository.clone() };
            let output_writer = JsonFileOutputWriter { path: args.output };

            let commits = commit_provider.fetch()?;

            // 3. Extract repo path for link generation
            let repo_path = if args.repository.starts_with("http") {
                let url = Url::parse(&args.repository)?;
                url.path().trim_start_matches('/').trim_end_matches(".git").to_string()
            } else {
                args.repository
            };

            let data = parse_commits(&commits, &repo_path)?;

            output_writer.write(&data)?;

            println!("Repository parsed successfully!");
        }
    }

    Ok(())
}