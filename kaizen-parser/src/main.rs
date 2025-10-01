use clap::Parser;
use kaizen::{Cli, run};

fn main() -> Result<(), Box<dyn std::error::Error>> {
	env_logger::init();
	let cli = Cli::parse();
	run(cli)
}
