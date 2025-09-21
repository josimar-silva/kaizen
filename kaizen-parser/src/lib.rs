pub mod adapters;
pub mod domain;

use std::path::PathBuf;

use clap::{Parser, Subcommand};

use crate::adapters::git_provider::GitCommitProvider;
use crate::adapters::json_writer::JsonFileOutputWriter;
use crate::domain::kaizen::KaizenData;
use crate::domain::parser::parse_commits;
use crate::domain::ports::{CommitProvider, OutputWriter};
use crate::domain::stats::calculate_stats;

#[derive(Parser)]
#[command(author, version, about, long_about = None)]
pub struct Cli {
	#[command(subcommand)]
	pub command: Command,
}

#[derive(Subcommand)]
pub enum Command {
	/// Parses a git repository and generates a JSON data file.
	Parse(ParseArgs),
}

#[derive(Parser, Debug)]
pub struct ParseArgs {
	/// The git repository URL to parse.
	#[arg(long)]
	pub repository: String,

	/// The output file path for the generated JSON.
	#[arg(long)]
	pub output: PathBuf,
}

pub fn run(cli: Cli) -> Result<(), Box<dyn std::error::Error>> {
	match cli.command {
		Command::Parse(args) => {
			println!("Parsing repository: {}", args.repository);

			let commit_provider = GitCommitProvider {
				url: args.repository.clone(),
			};
			let output_writer = JsonFileOutputWriter { path: args.output };

			let commits = commit_provider.fetch()?;

			let repo_path = commit_provider.get_repo_path()?;

			let commits_by_date = parse_commits(&commits, &repo_path)?;

			println!("Calculating stats...");
			let stats = calculate_stats(&commits_by_date);
			println!("Stats calculated!");

			let kaizen_data = KaizenData {
				stats,
				commits: commits_by_date,
			};

			output_writer.write(&kaizen_data)?;

			println!("Repository parsed successfully!");
		}
	}

	Ok(())
}
