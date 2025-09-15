use crate::domain::commit::CommitData;
use crate::domain::ports::OutputWriter;
use std::collections::HashMap;
use std::fs::File;
use std::io::{self, Write};
use std::path::PathBuf;

pub struct JsonFileOutputWriter {
    pub path: PathBuf,
}

impl OutputWriter for JsonFileOutputWriter {
    type Error = io::Error;

    fn write(&self, data: &HashMap<String, Vec<CommitData>>) -> Result<(), Self::Error> {
        let json_output = serde_json::to_string_pretty(data).map_err(|e| io::Error::new(io::ErrorKind::Other, e))?;
        let mut file = File::create(&self.path)?;
        file.write_all(json_output.as_bytes())?;
        Ok(())
    }
}
