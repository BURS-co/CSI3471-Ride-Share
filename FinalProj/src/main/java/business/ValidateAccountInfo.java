package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import data.databaseControllers.UserDatabase;
import data.user.User;

public class ValidateAccountInfo {

	/**
	 * @param name, email, phone, password, reenterPass, AccountCreateDialog
	 * @return succeeded
	 */
	public static boolean validateAccountInfoEntered(String name, String email, String phone, String password,
			String reenterPass, String gradMonth, String gradYear) {

		if (name == null || name.length() == 0 || email == null || email.length() == 0 || phone == null
				|| phone.length() == 0 || password == null || password.length() == 0 || reenterPass == null
				|| reenterPass.length() == 0) {
			JOptionPane.showMessageDialog(null, "Fields must not be empty, please fill in all fields.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// validate name must be first and last
		String[] username = name.split(" ");
		if (username.length != 2 || username[0].equals(username[1])) {
			JOptionPane.showMessageDialog(null, "Invalid name. Enter first and last name.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		
	    Matcher matcher = pattern.matcher(password);
	    boolean containsSpecialChars = matcher.matches();

		// validate email

<<<<<<< HEAD
		int emailSize = email.length() - 11;
		if (emailSize > 1) {
			String partOfEmail = email.substring(emailSize, email.length());
			if (!(partOfEmail.toLowerCase().matches("@baylor.edu"))) {
				JOptionPane.showMessageDialog(null, "Invalid email address. Must be a valid Baylor email.", "Create Account",
=======
		String partOfEmail = email.substring(emailSize, email.length());
		if (!(password.equals(reenterPass))) {
			JOptionPane.showMessageDialog(a, "Your passwords do not match! Try again.", "Login",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (password.length() < 8 || !numberFlag || !lowerCaseFlag || !capitalFlag) {
			JOptionPane.showMessageDialog(a,
					"Password must contain >8 characters and at least 1 uppercase, 1 lowercase, and a number. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (!containsSpecialChars) { 
			JOptionPane.showMessageDialog(a,
					"Password must not contain a special character. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (phone.length() != 10) {
			JOptionPane.showMessageDialog(a, "Invalid phone number. Must be 10 digits.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (!(partOfEmail.toLowerCase().matches("@baylor.edu")) || emailSize <= 1) {
			JOptionPane.showMessageDialog(a, "Invalid email address. Must be a valid Baylor email.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (emailUsed) {
			JOptionPane.showMessageDialog(a, "Email is already in use.", "Create Account", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (gradMonthSelect < month && gradYearSelect == year) {
			JOptionPane.showMessageDialog(a, "Invalid graduation month/year.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (name != null) {
			String username = name;
			int words = 0;
			if (!(" ".equals(username.substring(0, 1))) || !(" ".equals(username.substring(username.length() - 1)))) {
				for (int i = 0; i < username.length(); i++) {
					if (username.charAt(i) == ' ') {
						words++;
					}
				}
				words = words + 1;
			}
			if (words != 2) {
				JOptionPane.showMessageDialog(a, "Invalid user name. Enter first and last name.", "Create Account",
>>>>>>> refs/remotes/origin/master
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid email address. Must be a valid Baylor email.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// this could probably be a UserDatabase method @backend
		// UserDatabase uDat = new UserDatabase();
		// uDat.checkEmailInUse(); boolean returning
		// also this should be checked last to avoid garbage
		ArrayList<User> users = UserDatabase.getInstance().getUserData();
<<<<<<< HEAD
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().toLowerCase().equals(email.toLowerCase())) {
				JOptionPane.showMessageDialog(null, "Email is already in use.", "Create Account",
=======
		boolean emailUsed = false;
		for (User u : users) {
			if (u.getEmail().toLowerCase().equals(email.toLowerCase())) {
				emailUsed = true;
			}
		}

		int emailSize = 0;
		emailSize = email.length() - 11;
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		for (int i = 0; i < password.length(); i++) {
			ch = password.charAt(i);
			if (Character.isDigit(ch)) {
				numberFlag = true;
			} else if (Character.isUpperCase(ch)) {
				capitalFlag = true;
			} else if (Character.isLowerCase(ch)) {
				lowerCaseFlag = true;
			}
		}
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		
	    Matcher matcher = pattern.matcher(password);
	    boolean containsSpecialChars = matcher.matches();

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int gradMonthSelect = Integer.parseInt(gradMonth);
		int gradYearSelect = Integer.parseInt(gradYear);

		String partOfEmail = email.substring(emailSize, email.length());
		if (!(password.equals(reenterPass))) {
			JOptionPane.showMessageDialog(e, "Your passwords do not match! Try again.", "Login",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (password.length() < 8 || !numberFlag || !lowerCaseFlag || !capitalFlag) {
			JOptionPane.showMessageDialog(e,
					"Password must contain >8 characters and at least 1 uppercase, 1 lowercase, and a number. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (!containsSpecialChars) { 
			JOptionPane.showMessageDialog(e,
					"Password must not contain a special character. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (phone.length() != 10) {
			JOptionPane.showMessageDialog(e, "Invalid phone number. Must be 10 digits.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (gradMonthSelect < month && gradYearSelect == year) {
			JOptionPane.showMessageDialog(e, "Invalid graduation month/year.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (name != null) {
			String username = name;
			int words = 0;
			if (!(" ".equals(username.substring(0, 1))) || !(" ".equals(username.substring(username.length() - 1)))) {
				for (int i = 0; i < username.length(); i++) {
					if (username.charAt(i) == ' ') {
						words++;
					}
				}
				words = words + 1;
			}
			if (words != 2) {
				JOptionPane.showMessageDialog(e, "Invalid user name. Enter first and last name.", "Create Account",
>>>>>>> refs/remotes/origin/master
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		// validate phone
		if (phone.length() != 10) {
			JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// validate password
		Pattern p = Pattern.compile("((?=.*[a-z])(?=.*d)(?=.*[!@#$%])(?=.*[A-Z]).{8,12})");
		Matcher m = p.matcher(password);

		if (!m.matches()) {
			JOptionPane.showMessageDialog(null,
					"Password must contain >8 characters, at least 1 uppercase, at least 1 lowercase,at least 1 number, "
							+ "and 1 special symbol. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// passwords must match
		if (!(password.equals(reenterPass))) {
			JOptionPane.showMessageDialog(null, "Your passwords do not match! Try again.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Integer gradMonthSelect = Integer.parseInt(gradMonth);
		Integer gradYearSelect = Integer.parseInt(gradYear);

		if (gradMonthSelect < month && gradYearSelect == year) {
			JOptionPane.showMessageDialog(null, "Invalid graduation month/year.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		return true;
	}
}