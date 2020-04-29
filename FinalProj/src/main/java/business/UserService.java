package business;

import data.databaseControllers.UserDatabase;
import data.user.User;

public class UserService {
	
	public static User CreateUser(String name, String email, String phone, String password, String gradMonth, String gradYear) {
		
		User user = new User();
		user.setUsername(name);
		user.setEmail(email);
		user.setPhoneNumber(phone);
		user.setPassword(password);
		user.setGradMonth(gradMonth);
		user.setGradYear(gradYear);
		
		UserDatabase.getInstance().add(user);
		
		return null;	
	}

}
