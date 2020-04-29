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

			String line = null;
			ArrayList<Prospects> list = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split("-");
				AbstractPost p = null;

				if (split[0].equals("Driver")) {
					p = new Driver();
				} else {
					p = new Rider();
					if (split.length > 5)
						((Rider) p).setDriver(split[5]);
				}

				p.setPoster(split[1]);
				p.setOrigin(split[2]);
				p.setDest(split[3]);

				Date d = new SimpleDateFormat("dd MMM yyyy hh:mm a").parse(split[4]);
				p.setDate(d);

				if (p instanceof Driver) {
					((Driver) p).setRiderLimit(Integer.valueOf(split[5]));

					for (int i = 6; i < split.length - 1; i++) {
						if (list == null) {
							list = new ArrayList<Prospects>();
						}

						Prospects temp = new Prospects();

						temp.setName(split[i++]);
						temp.setStatus(split[i]);

						list.add(temp);
					}

					((Driver) p).setRiders(list);
					//PostService.getInstance().addProspects(UserService.getInstance().getCurrentUser(), p.getPoster());
				}
				postData.add(p);
				if (list != null && list.size() > 0) {
					list.clear();
				}
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

	final public static AbstractPost queryDatabase(String post) {
		// The queryDatabase could be instead searching for a specific post
		AbstractPost result = null;

		for (AbstractPost p : postData) {
			if (false/* deterministic character of post like id */) {
				result = p;
			}
		}
		return result;
	}

	/**
	 * @param type
	 * @return an array of post of said type
	 */
	final public ArrayList<AbstractPost> searchDatabase(String type) {
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
						if (((Driver) p).getRiders().contains(type))
							query.add(p);
			}
		}

		return query;
	}

	public static void addPost(AbstractPost p) {
		postData.add(p);
	}

}
