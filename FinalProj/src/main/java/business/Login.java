/**
 * @author Andrew Ammentorp, Joseph Perez
 */
package business;

import java.util.ArrayList;

import data.databaseControllers.UserDatabase;
import data.user.Admin;
import data.user.User;
import presentation.application.Application;

public class Login {
	//private static User user;

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {
		
		UserService.getInstance().setCurrentUser(UserDatabase.getInstance().queryDatabase(email, password));
		
		return UserService.getInstance().getCurrentUser() != null;
//		ArrayList<User> users = UserDatabase.getInstance().getUserData();
//		boolean success = false;
//
//		// search until found.
//		for (int i = 0; i < users.size() && !success; i++) {
//			if (users.get(i).getEmail().toLowerCase().equals(email.toLowerCase())
//					&& users.get(i).getPassword().equals(password)) {
//				 //Set user data
//				if (users.get(i) instanceof Admin) {
//					Application.loggedIn = new Admin();
//				} else {
//					Application.loggedIn = new User();
//				}
//
//				Application.loggedIn.setGradMonth(users.get(i).getGradMonth());
//				Application.loggedIn.setGradYear(users.get(i).getGradYear());
//				Application.loggedIn.setEmail(email.toLowerCase());
//				Application.loggedIn.setPassword(password);
//				Application.loggedIn.setPhoneNumber(users.get(i).getPhoneNumber());
//				Application.loggedIn.setUsername(users.get(i).getUsername());
//
//				UserService.getInstance().setCurrentUser(users.get(i));
//
//				success = true;
//			}
//		}
//
//		return success;

	}

}
