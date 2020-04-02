package data.databaseControllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.user.Admin;
import data.user.User;

public class UserDatabase{
	static ArrayList<User> userData = new ArrayList<User>();
	
	public static void load() throws IOException {
		//In order username email phone number password isAdmin
		//open file
		try {
			BufferedReader loader = new BufferedReader(new FileReader (new File(
			        "userDatabase.txt")));
			
			String line = null;
			
			while ((line = loader.readLine()) != null) {
				
				String[] split = line.split(" ");
				User u = null;
				
				if(split[5].equals("true")) {
					u = new Admin();
				} else {
					u = new User();
				}
				
				for(int i = 0; i < split.length; i++) {
					if(i == 0) {
						u.setUsername(split[i] + " " + split[i+1]);
					}
				    else if(i == 2) {
						u.setEmail(split[i]);
					}
					else if(i == 3) {
						u.setPhoneNumber(split[i]);
					}
					else if(i == 4) {
						u.setPassword(split[i]);
					}
				}
				//Add data
				userData.add(u);
			}
			
			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write() throws IOException {
		//open file
		BufferedWriter write = new BufferedWriter(new FileWriter(
                "userDatabase.txt"));
		
		for(User u : userData) {
			write.write(u.toString());
		}
		
		write.flush();
		write.close(); 	
	}
	
	public void add(User u) {
		userData.add(u);
	}
	
	public static ArrayList<User> getUserData(){
		return userData;
	}
	
}