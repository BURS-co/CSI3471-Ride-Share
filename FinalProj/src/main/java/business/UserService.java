package business;

import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.UserDatabase;
import data.user.User;
import enums.Failures;

public class UserService implements IService {
    //this is the service class for user
	private static User currentUser;
	private static UserService userService = null;
	private static ReentrantLock lock = new ReentrantLock();
	// private SurveyDatabase database;

	private UserService() {
		// database = SurveyDatabase.getInstance();
	}

	public static UserService getInstance() {
		if (userService == null) {
			lock.lock();
			if (userService == null)
				userService = new UserService();
		}

		return userService;
	}

	public Failures verify(String[] list) {
		// boolean result = true;
		Failures result = Failures.SUCCESS;
		if (list.length != 0) {
			for (String field : list) {
				if (field.isEmpty()) {
					// result = false;
					result = Failures.emptyField;
					break;
				}
			}
		} else {
			// result = false;
			result = Failures.emptyField;
		}

		if(result == Failures.emptyField) {
			return result;
		}
		
		// more validation tests...

		// store survey if it was successfully validated
		if (result == Failures.SUCCESS) {

			store(list);
		}
		return result;
	}

	public User create(String[] list) {
		// create the user
		User user = new User();

		user.setUsername(list[0]);
		user.setEmail(list[1]);
		user.setPhoneNumber(list[2]);
		user.setPassword(list[3]);
		user.setGradMonth(list[4]);
		user.setGradYear(list[5]);

		UserService.currentUser = user;
		
		return user;
	}

	public void store(String[] list) {
		UserDatabase.addUser(create(list));
	}
	
	public User getCurrentUser() {
		return UserService.currentUser;
	}
	
	public void setCurrentUser(User c) {
		UserService.currentUser = c;
	}

}
