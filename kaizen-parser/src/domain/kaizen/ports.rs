use crate::domain::kaizen::entities::KaizenData;

pub trait OutputWriter {
	type Error;
	fn write(&self, data: &KaizenData) -> Result<(), Self::Error>;
}
