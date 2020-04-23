package business;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.user.User;
import presentation.application.AccountCreateDialog;
import presentation.application.Application;
import presentation.application.EditProfile;

public class ValidateAccountInfo extends AccountCreateDialog {
	static boolean succeeded = false;

	/**
	 * @param parent
	 * @return
	 */
	public ValidateAccountInfo(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name, email, phone, password, reenterPass, AccountCreateDialog
	 * @return succeeded
	 */
	public static boolean validateAccountInfoEntered(String name, String email, String phone, String password,
			String reenterPass, String gradMonth, String gradYear, AccountCreateDialog a) {
		ArrayList<User> users = Application.userDatabase.getUserData();
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

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int gradMonthSelect = Integer.parseInt(gradMonth);
		int gradYearSelect = Integer.parseInt(gradYear);

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
						JOptionPane.INFORMATION_MESSAGE);
				succeeded = false;
			} else {
				succeeded = true;

			}
		}
		return succeeded;
	}

	public static boolean validateAccountInfoEntered(String name, String email, String phone, String password,
			String reenterPass, String gradMonth, String gradYear, EditProfile e) {
		ArrayList<User> users = Application.userDatabase.getUserData();
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
						JOptionPane.INFORMATION_MESSAGE);
				succeeded = false;
			} else {
				succeeded = true;

			}
		}
		return succeeded;
	}
}
