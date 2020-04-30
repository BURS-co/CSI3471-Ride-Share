/**
 * @author Andrew Ammentorp, Joseph Perez
 */
package business;

import data.databaseControllers.UserDatabase;
import data.post.AbstractPost;

public class Login {
	// private static User user;

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {

		UserService.getInstance().setCurrentUser(UserDatabase.getInstance().queryDatabase(email, password));
		
		return UserService.getInstance().getCurrentUser() != null;
	}

}
