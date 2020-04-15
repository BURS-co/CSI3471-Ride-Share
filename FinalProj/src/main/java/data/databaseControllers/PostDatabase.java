package data.databaseControllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import data.post.DriverPost;
import data.post.Post;

public class PostDatabase {
	//singleton
	private static PostDatabase postDatabase = null;
	
	private static ReentrantLock lock = new ReentrantLock();
	private PostDatabase() { }
	
	public static PostDatabase getInstance() {
		if(postDatabase == null) {
			lock.lock();
			if(postDatabase == null)
				postDatabase = new PostDatabase();
		}
			
		return postDatabase;
	}
	
	private static ArrayList<Post> postData = new ArrayList<Post>();
	
	public void load() throws ParseException, IOException {
		try {
			BufferedReader loader = new BufferedReader(new FileReader (new File(
			        "postDatabase.txt")));
			
			String line = null;
			ArrayList<String> list = null;
			
			while ((line = loader.readLine()) != null) {
				
				String[] split = line.split("-");
				Post p = null;
				for(int i = 0; i < split.length; i++) {
					if(i == 0) {
						if(split[i].equals("driver")) {
							p = new DriverPost();
						}
						else {
							p = new Post();
						}
						p.setType(split[i]);
					} else if (i == 1) {
						p.setPoster(split[i]);
					} else if(i == 2) {
						p.setOrigin(split[i]);
					} else if(i == 3) {
						p.setDest(split[i]);
					} else if(i == 4) {
						Date d = new SimpleDateFormat("E, MMM dd yy hh:mm").parse(split[i]);
						p.setDate(d);
					} else if (p.getType().equals("driver") && i == 5) {
						((DriverPost) p).setDriver(split[i]);
					} else if (p.getType().equals("driver") && i == 6) {
						((DriverPost) p).setRiderLimit(Integer.valueOf(split[i]));
					} else if (p.getType().equals("driver") && i > 7){
						if (list == null) {
							list = new ArrayList<String>();
						}
						list.add(split[i]);
					}
				}
				//Add data
				if (p instanceof DriverPost) {
					((DriverPost) p).setRiders(list);
				}
				postData.add(p);
			}
			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void write() throws IOException {
		//Write to .txt file (postDatabase.txt)
		BufferedWriter write = new BufferedWriter(new FileWriter(
                "postDatabase.txt"));
		
		for(Post p : postData) {
			write.write(p.toString());
		}
		
		write.flush();
		write.close(); 	
		
	}
	
	public void add(Post p) {
		postData.add(0, p);
	}


	public static ArrayList<Post> getPostData() {
		return postData;
	}
	
	final public ArrayList<Post> queryDatabase (){
		//The queryDatabase could be instead searching for a specific post
		
		return null;
	}
	
	/**
	 * @param type
	 * @return an array of post of said type
	 */
	final public ArrayList<Post> searchDatabase (String type) {
		ArrayList<Post> query = new ArrayList<Post>();
		
		if(type.equals("rider") || type.equals("driver")) {
			for (Post p: postData) {
				if (p.getType().equals(type)) {
					query.add(p);
				}
			}
		}
		
		return query;
	}
	
}
