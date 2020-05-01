package data.databaseControllers;

import business.WriteToFile;

public abstract class AbstractDatabase {
	private WriteToFile writer;

	public WriteToFile getWriter() {
		return writer;
	}

	public void setWriter(WriteToFile writer) {
		this.writer = writer;
	}
}
