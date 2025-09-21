use std::fs::File;
use std::io::{self, Write};
use std::path::PathBuf;

use crate::domain::kaizen::KaizenData;
use crate::domain::ports::OutputWriter;

pub struct JsonFileOutputWriter {
	pub path: PathBuf,
}

impl OutputWriter for JsonFileOutputWriter {
	type Error = io::Error;

	fn write(&self, data: &KaizenData) -> Result<(), Self::Error> {
		let json_output =
			serde_json::to_string_pretty(data).map_err(io::Error::other)?;
		let mut file = File::create(&self.path)?;
		file.write_all(json_output.as_bytes())?;
		Ok(())
	}
}
