/**
 * @author Andrew
 */
package business;

import java.util.ArrayList;

import data.databaseControllers.UserDatabase;
import data.user.User;
import presentation.application.Globals;
import presentation.application.Runner;

public class Login {
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {
		ArrayList<User> users = Runner.userDatabase.getUserData();
		//ArrayList<User> users = UserDatabase.getUserData();
		
		for(User u : users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					//Set user data
					Globals.loggedIn.setEmail(email);
					Globals.loggedIn.setPassword(password);
					Globals.loggedIn.setPhoneNumber(u.getPhoneNumber());
					Globals.loggedIn.setUsername(u.getUsername());
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		//Searched entire database, but no email match
		return false;
		
    }

}
