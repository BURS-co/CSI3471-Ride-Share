package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import data.databaseControllers.IWrite;

public class WriteToFile {
    public void write(IWrite<?> source, String dest) throws IOException {
    	//source.write();
    	BufferedWriter out = new BufferedWriter(new FileWriter(dest));
    	
    	for(Object o : source.getData()) {
    		out.write(o.toString());
    	}
    	
    	out.flush();
    	out.close();
    }
}
