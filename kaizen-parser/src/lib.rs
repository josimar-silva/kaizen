pub mod adapters;
pub mod domain;

use std::path::PathBuf;

use clap::{Parser, Subcommand};

use crate::adapters::git_provider::GitCommitProvider;
use crate::adapters::json_writer::JsonFileOutputWriter;
use crate::domain::analysis::AnalysisFiles;
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

	/// Directories to search for analysis markdown files.
	///
	/// Expects files to be named like 'NNNN-description-HASH.md', where HASH
	/// is the 7-character commit ID.
	///
	/// Example: --analysis-dirs=algorithms/analysis,docs/system-design
	#[arg(long, value_delimiter = ',', num_args = 1..)]
	pub analysis_dirs: Option<Vec<PathBuf>>,
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

			let analysis_files = AnalysisFiles::new(
				args.analysis_dirs.as_deref().unwrap_or_default(),
			)
			.map_err(|e| {
				format!(
					"Failed to list analysis files in {:?}: {e}. Ensure the \
					 directory exists and is readable.",
					args.analysis_dirs
				)
			})?;

			let commits_by_date =
				parse_commits(&commits, &repo_path, &analysis_files)?;

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
