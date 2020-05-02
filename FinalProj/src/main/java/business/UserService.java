/**
 * @author Joseph Yu, Joshua Huertas
 */
package business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.databaseControllers.UserDatabase;
import data.user.User;
import enums.Failures;

public class UserService implements IService {
	// this is the service class for user
	private User currentUser;
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

		if (result == Failures.emptyField) {
			return result;
		}

		// more validation tests...
		String[] username = list[0].split(" ");
		if (username.length != 2 || username[0].equals(username[1])) {
			result = Failures.invalidName;
			return result;
		}

		int emailSize = list[1].length() - 11;
		if (emailSize > 1) {
			String partOfEmail = list[1].substring(emailSize, list[1].length());
			if (!(partOfEmail.toLowerCase().matches("@baylor.edu"))) {
				result = Failures.invalidEmail;
				return result;
			}

		} else {
			result = Failures.invalidEmail;
			return result;
		}

		ArrayList<User> users = UserDatabase.getInstance().getUserData();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().toLowerCase().equals(list[1].toLowerCase())) {
				result = Failures.emailInUse;
				return result;
			}
		}

		if (list[2].length() != 10) {
			result = Failures.invalidPhoneNumber;
			return result;
		}

		Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[!@#$%])(?=.*[A-Z]).{8,})");
		Matcher m = p.matcher(list[3]);
		if (!m.matches()) {
			result = Failures.invalidPasswordStandard;
			return result;
		}

		if (!(list[3].equals(list[4]))) {
			result = Failures.passwordMismatch;
			return result;
		}

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Integer gradMonthSelect = Integer.parseInt(list[5]);
		Integer gradYearSelect = Integer.parseInt(list[6]);
		if (gradMonthSelect < month && gradYearSelect <= year) {
			result = Failures.invalidGraduationDate;
			return result;
		}

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

		this.currentUser = user;

		return user;
	}

	public void store(String[] list) {
		UserDatabase.getInstance().add(create(list));
	}

	public User getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(User c) {
		this.currentUser = c;
	}

	public Failures update(String[] list) {
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

		if (result == Failures.emptyField) {
			return result;
		}

		// more validation tests...
		String[] username = list[0].split(" ");
		if (username.length != 2 || username[0].equals(username[1])) {
			result = Failures.invalidName;
			return result;
		}

		if (list[1].length() != 10) {
			result = Failures.invalidPhoneNumber;
			return result;
		}

		Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[!@#$%])(?=.*[A-Z]).{8,})");
		Matcher m = p.matcher(list[2]);
		if (!m.matches()) {
			result = Failures.invalidPasswordStandard;
			return result;
		}

		if (!(list[2].equals(list[3]))) {
			result = Failures.passwordMismatch;
			return result;
		}

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Integer gradMonthSelect = Integer.parseInt(list[4]);
		Integer gradYearSelect = Integer.parseInt(list[5]);
		if (year != null && month != null && gradMonthSelect != null && gradYearSelect != null) {
			if (gradMonthSelect < month && gradYearSelect == year) {
				result = Failures.invalidGraduationDate;
				return result;
			}
		}

//		for(String i : list) {
//			System.out.println(i);
//		}

		// store survey if it was successfully validated
		if (result == Failures.SUCCESS) {
			change(list);
		}
		return result;

	}

	private void change(String[] list) {

		this.getCurrentUser().setUsername(list[0]);
		this.getCurrentUser().setPhoneNumber(list[1]);
		this.getCurrentUser().setPassword(list[2]);
		this.getCurrentUser().setGradMonth(list[4]);
		this.getCurrentUser().setGradYear(list[5]);

	}

	public void resetJoinNotif() {
		this.currentUser.setJoinNotif(false);
		try {
			UserDatabase.getInstance().write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void resetCanceledNotif() {
		this.currentUser.setPostCanceledNotif(false);
		try {
			UserDatabase.getInstance().write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
