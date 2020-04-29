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

		boolean flag = false;
		
		if(UserService.getInstance().getCurrentUser() != null) {
			flag = true;
			
			for(AbstractPost i : UserService.getInstance().getCurrentUser().getPosts()) {
				if(i.isExpired()) {
					//trigger pop up for survey and pass the results to survey service
				}
			}
			
		}
		
		
		return flag;

	}

}
