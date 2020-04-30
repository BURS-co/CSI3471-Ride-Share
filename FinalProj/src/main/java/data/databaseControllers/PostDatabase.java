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

import business.PostService;
import business.UserService;
import data.post.AbstractPost;
import data.post.Driver;
import data.post.Prospects;
import data.post.Rider;

public class PostDatabase {
	// singleton
	private static PostDatabase postDatabase = null;

	private static ReentrantLock lock = new ReentrantLock();

	private PostDatabase() {
	}

	public static PostDatabase getInstance() {
		if (postDatabase == null) {
			lock.lock();
			if (postDatabase == null)
				postDatabase = new PostDatabase();
		}

		return postDatabase;
	}

	private static ArrayList<AbstractPost> postData = new ArrayList<AbstractPost>();

	public void load() throws ParseException, IOException {
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File("postDatabase.txt")));
			AbstractPost p = null;
			String line = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split("-");

				if (split[0].equals("Driver")) {
					p = new Driver(split[1]);
				} else {
					p = new Rider(split[1]);
					if (split.length > 6) {
						Prospects temp = new Prospects();
					
						temp.setName(split[6]);
						temp.setStatus(split[7]);
						
						((Rider) p).setDriver(temp);
					}
				}

				p.setPoster(split[2]);
				p.setOrigin(split[3]);
				p.setDest(split[4]);

				Date d = new SimpleDateFormat("dd MMM yyyy hh:mm a").parse(split[5]);
				p.setDate(d);

				if (p instanceof Driver) {
					((Driver) p).setRiderLimit(Integer.valueOf(split[6]));
					ArrayList<Prospects> list = null;
					for (int i = 7; i < split.length - 1; i++) {
						list = new ArrayList<Prospects>();

						Prospects temp = new Prospects();

						temp.setName(split[i++]);
						temp.setStatus(split[i]);

						list.add(temp);
					}

					((Driver) p).setRiders(list);
					//PostService.getInstance().addProspects(UserService.getInstance().getCurrentUser(), p.getPoster());
				}
				postData.add(p);
			}
			loader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void write() throws IOException {
		// Write to .txt file (postDatabase.txt)
		BufferedWriter write = new BufferedWriter(new FileWriter("postDatabase.txt"));

		for (AbstractPost p : postData) {
			write.write(p.toString());
		}

		write.flush();
		write.close();

	}

	public static ArrayList<AbstractPost> getPostData() {
		return postData;
	}

	final public static AbstractPost searchDatabase(int ID) {
		// The queryDatabase could be instead searching for a specific post
		AbstractPost result = null;

		for (AbstractPost p : postData) {
			if (p.getID() == ID) {
				result = p;
			}
		}
		return result;
	}

	/**
	 * @param type
	 * @return an array of post of said type
	 */
	final public ArrayList<AbstractPost> quereyDatabase(String type) {
		ArrayList<AbstractPost> query = new ArrayList<AbstractPost>();

		for (AbstractPost p : postData) {
			if (type.equals("rider")) {
				if (p instanceof Rider) {
					query.add(p);
				}
			} else if (type.equals("driver")) {
				if (p instanceof Driver) {
					query.add(p);
				}
			} else {
				if (p instanceof Rider) {
					if (type.equalsIgnoreCase(((Rider) p).getDriver()))
						query.add(p);
				} else if (p instanceof Driver)
					if (((Driver) p).getRiders() != null)
						for(Prospects pr : ((Driver) p).getRiders())
							if (pr.getName().equalsIgnoreCase(type))
								query.add(p);
			}
		}

		return query;
	}

	public static void addPost(AbstractPost p) {
		postData.add(p);
	}

}
