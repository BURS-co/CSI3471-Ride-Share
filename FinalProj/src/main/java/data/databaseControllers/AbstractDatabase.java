/**
 * @author Joseph Yu
 */
package data.databaseControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import business.WriteToFile;

public abstract class AbstractDatabase {
	private WriteToFile writer;
	
	public AbstractDatabase() {
		this.writer = new WriteToFile();
	}

	public WriteToFile getWriter() {
		return writer;
	}

	public void setWriter(WriteToFile writer) {
		this.writer = writer;
	}
	
	public void loadFromFile(String file) {
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File(file)));
			
			String line = null;
			
			while ((line = loader.readLine()) != null) {
				String[] split = line.split(getDelimiter());
				
				Object item = makeItem(split);
				add(item);
			}
			
			loader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract String getDelimiter();
	public abstract void add(Object item);
	public abstract Object makeItem(String[] list);
}
