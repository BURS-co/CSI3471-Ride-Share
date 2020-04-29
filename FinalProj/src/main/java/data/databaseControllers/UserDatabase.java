package data.databaseControllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import data.user.Admin;
import data.user.User;

public class UserDatabase {

	// singleton instance
	private static UserDatabase userDatabase = null;// new UserDatabase();
	// multi-thread protection
	private static ReentrantLock lock = new ReentrantLock();
	// array to hold users
	private ArrayList<User> userData = new ArrayList<User>();

	// private constructor
	private UserDatabase() {
	}

	/**
	 * initialize an instance of the user database if none exists
	 * 
	 * @return UserDatabase
	 */
	public static UserDatabase getInstance() {
		if (userDatabase == null) {
			lock.lock();
			if (userDatabase == null)
				userDatabase = new UserDatabase();
		}

		return userDatabase;
	}

	/**
	 * adds user to the database
	 * 
	 * @param User u
	 */
	public void addUser(User u) {
		userData.add(u);
	}

	/**
	 * removes user from the database
	 * 
	 * @param u
	 */
	public void removeUser(User u) {
		userData.remove(u);
	}

	/**
	 * loads all users from database
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		// In order username email phone number password isAdmin
		// open file
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File("userDatabase.txt")));

			String line = null;
			User u = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split(" ");

				if (split[7].equals("true")) {
					u = new Admin();
				} else {
					u = new User();
				}

				for (int i = 0; i < split.length; i++) {
					if (i == 0) {
						u.setUsername(split[i] + " " + split[i + 1]);
					} else if (i == 2) {
						u.setEmail(split[i]);
					} else if (i == 3) {
						u.setPhoneNumber(split[i]);
					} else if (i == 4) {
						u.setGradMonth(split[i]);
					} else if (i == 5) {
						u.setGradYear(split[i]);
					} else if (i == 6) {
						u.setPassword(split[i]);
					}
				}
				// Add data
				userData.add(u);
			}

			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * write to the database
	 * 
	 * @throws IOException
	 */
	public void write() throws IOException {
		// open file
		BufferedWriter write = new BufferedWriter(new FileWriter("userDatabase.txt"));

		for (User u : userData) {
			write.write(u.toString());
		}

		write.flush();
		write.close();
	}

	public void add(User u) {
		userData.add(u);
	}

	public ArrayList<User> getUserData() {
		return userData;
	}

}