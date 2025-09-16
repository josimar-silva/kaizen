use clap::Parser;
use kaizen::{Cli, run};

fn main() -> Result<(), Box<dyn std::error::Error>> {
	let cli = Cli::parse();
	run(cli)
}
