/**
 * @author Andrew
 */
package business;

import java.util.ArrayList;

import data.user.User;
import presentation.application.Application;

public class Login {
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {
		ArrayList<User> users = Application.userDatabase.getUserData();
		//ArrayList<User> users = UserDatabase.getUserData();
		
		for(User u : users) {
			if(u.getEmail().toLowerCase().equals(email.toLowerCase())) {
				if(u.getPassword().equals(password)) {
					//Set user data
					Application.loggedIn.setEmail(email);
					Application.loggedIn.setPassword(password);
					Application.loggedIn.setPhoneNumber(u.getPhoneNumber());
					Application.loggedIn.setUsername(u.getUsername());
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
