/**
 * @author Andrew
 */
package business;

import java.util.ArrayList;

import data.databaseControllers.UserDatabase;
import data.user.User;
import presentation.application.Globals;

public class Login {
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {
		ArrayList<User> users = UserDatabase.getUserData();
		//boolean validate = false;
		
		for(User u : users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					//validate = true;
					//Set user data
					Globals.loggedIn.setEmail(email);
					Globals.loggedIn.setPassword(password);
					Globals.loggedIn.setPhoneNumber(u.getPhoneNumber());
					Globals.loggedIn.setUsername(u.getUsername());
					return true;
				}
				else {
					return false;
					//validate = false;
				}
			}
		}
		
		//Searched entire database, but no email match
		return false;
		
		//return validate;
		
        /*if (username.equals("bob") && password.equals("secret")) {
            return true;
        }
        return false;*/
    }

}
