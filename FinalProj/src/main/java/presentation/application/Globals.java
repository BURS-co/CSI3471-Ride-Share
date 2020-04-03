package presentation.application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import data.user.User;

public class Globals {
	public final static Logger log = Logger.getLogger(Runner.class.getName());
	static FileHandler fh;  
	public static boolean accountCreated = false;
	public static boolean postCreated = false;
	public static User loggedIn = new User();
	static {
		 try {  
			 
		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("projectLog.txt",true);  
		        log.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		        // the following statement is used to log any messages  
		        //logger.info("My first log");  

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
	}
}
