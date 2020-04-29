package business;

import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.SurveyDatabase;
import data.databaseControllers.UserDatabase;
import data.survey.Survey;
import data.user.User;
import enums.Failures;

public class UserService {
	
	private static UserService userService = null;
	private static ReentrantLock lock = new ReentrantLock();
	//private SurveyDatabase database;

	private UserService() {
		//database = SurveyDatabase.getInstance();
	}

	public static UserService getInstance() {
		if (userService == null) {
			lock.lock();
			if (userService == null)
				userService = new UserService();
		}

		return userService;
	}

	public Failures verifySurvey(String[] list) {
		//boolean result = true;
		Failures result = Failures.SUCCESS;
		if (list.length != 0) {
			for (String field : list) {
				if (field.isEmpty()) {
					//result = false;
					result = Failures.emptyField;
					break;
				}
			}
		} else {
			//result = false;
			result = Failures.emptyField;
		}

		// more validation tests...
		try {
			Integer.valueOf(list[2]);
		} catch (Exception e){
			//result = false;
			result = Failures.SurveyField2notANumber;
		}
		
		if(list[3].length() > 300) {
			//result = false;
			result = Failures.SurveyField3TooLong;
		}

		// store survey if it was successfully validated
		if (result == Failures.SUCCESS) {
			
			storeSurvey(list);
		}
		return result;
	}

	private Survey createSurvey(String[] list) {
		//create the survey
		Survey surv = new Survey();
		
		surv.setName(list[0]);
		surv.setTarget(list[1]);
		surv.setRating(Integer.valueOf(list[2]));
		surv.setComments(list[3]);
		
		return surv;
	}

	public void storeSurvey(String[] list) {
		SurveyDatabase.addSurvey(createSurvey(list));
	}
	
	
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
