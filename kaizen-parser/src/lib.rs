pub mod adapters;
pub mod domain;

use std::fs;
use std::path::PathBuf;

use clap::{Parser, Subcommand};

use crate::adapters::git_provider::GitCommitProvider;
use crate::adapters::json_writer::JsonFileOutputWriter;
use crate::domain::parser::parse_commits;
use crate::domain::ports::{CommitProvider, OutputWriter};
use crate::domain::stats::{calculate_stats, KaizenData};

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
	/// Calculates statistics from a generated JSON data file.
	Stats(StatsArgs),
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

#[derive(Parser, Debug)]
pub struct StatsArgs {
	/// The input JSON data file to calculate statistics from.
	#[arg(long)]
	pub input: PathBuf,

	/// The output file path for the generated statistics JSON.
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

			let data = parse_commits(&commits, &repo_path)?;

			output_writer.write(&data)?;

			println!("Repository parsed successfully!");
		}
		Command::Stats(args) => {
			println!("Calculating stats for: {:?}", args.input);

			let file_content = fs::read_to_string(args.input)?;
			let data: KaizenData = serde_json::from_str(&file_content)?;

			let stats = calculate_stats(&data);

			let stats_json = serde_json::to_string_pretty(&stats)?;
			fs::write(args.output, stats_json)?;

			println!("Statistics calculated successfully!");
		}
	}

	Ok(())
}
