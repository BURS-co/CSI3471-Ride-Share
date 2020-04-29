package business;

import javax.swing.JOptionPane;

import enums.Failures;

public class ValidateAccountInfo {

	/**
	 * @param name, email, phone, password, reenterPass, AccountCreateDialog
	 * @return succeeded
	 */
	public static boolean validateAccountInfoEntered(String name, String email, String phone, String password,
			String reenterPass, String gradMonth, String gradYear) {

		String[] info = { name, email, phone, password, reenterPass, gradMonth, gradYear };
		Failures result = UserService.getInstance().verify(info);

		switch (result) {
		case emptyField:
			JOptionPane.showMessageDialog(null, "Fields must not be empty, please fill in all fields.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidName:
			JOptionPane.showMessageDialog(null, "Invalid name. Enter first and last name.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidEmail:
			JOptionPane.showMessageDialog(null, "Invalid email address. Must be a valid Baylor email.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case emailInUse:
			JOptionPane.showMessageDialog(null, "Email is already in use.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidPhoneNumber:
			JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidPasswordStandard:
			JOptionPane.showMessageDialog(null,
					"Password must contain >8 characters, at least 1 uppercase, at least 1 lowercase,at least 1 number, "
							+ "and 1 special symbol. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			break;
		case passwordMismatch:
			JOptionPane.showMessageDialog(null, "Your passwords do not match! Try again.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidGraduationDate:
			JOptionPane.showMessageDialog(null, "Invalid graduation month/year.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			break;

		}

		return result == Failures.SUCCESS;

//		if (name == null || name.length() == 0 || email == null || email.length() == 0 || phone == null
//				|| phone.length() == 0 || password == null || password.length() == 0 || reenterPass == null
//				|| reenterPass.length() == 0) {
//			JOptionPane.showMessageDialog(null, "Fields must not be empty, please fill in all fields.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate name must be first and last
//		String[] username = name.split(" ");
//		if (username.length != 2 || username[0].equals(username[1])) {
//			JOptionPane.showMessageDialog(null, "Invalid name. Enter first and last name.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate email
//		int emailSize = email.length() - 11;
//		if (emailSize > 1) {
//			String partOfEmail = email.substring(emailSize, email.length());
//			if (!(partOfEmail.toLowerCase().matches("@baylor.edu"))) {
//				JOptionPane.showMessageDialog(null, "Invalid email address. Must be a valid Baylor email.", "Create Account",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Invalid email address. Must be a valid Baylor email.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// this could probably be a UserDatabase method @backend
//		// UserDatabase uDat = new UserDatabase();
//		// uDat.checkEmailInUse(); boolean returning
//		// also this should be checked last to avoid garbage
//		ArrayList<User> users = UserDatabase.getInstance().getUserData();
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getEmail().toLowerCase().equals(email.toLowerCase())) {
//				JOptionPane.showMessageDialog(null, "Email is already in use.", "Create Account",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//		}
//
//		// validate phone
//		if (phone.length() != 10) {
//			JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate password, must comply to regex
//
//		Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[!@#$%])(?=.*[A-Z]).{8,})");
//		Matcher m = p.matcher(password);
//		boolean matches = m.matches();
//
//		if (!matches) {
//			JOptionPane.showMessageDialog(null,
//					"Password must contain >8 characters, at least 1 uppercase, at least 1 lowercase,at least 1 number, "
//							+ "and 1 special symbol. Try again.",
//					"Create Account", JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// passwords must match
//		if (!(password.equals(reenterPass))) {
//			JOptionPane.showMessageDialog(null, "Your passwords do not match! Try again.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		Integer year = Calendar.getInstance().get(Calendar.YEAR);
//		Integer month = Calendar.getInstance().get(Calendar.MONTH);
//		Integer gradMonthSelect = Integer.parseInt(gradMonth);
//		Integer gradYearSelect = Integer.parseInt(gradYear);
//
//		if (gradMonthSelect < month && gradYearSelect == year) {
//			JOptionPane.showMessageDialog(null, "Invalid graduation month/year.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		return true;
	}

	public static boolean validateUpdateInfo(String name, String phone, String password, String reenterPass,
			String gradMonth, String gradYear) {

		String[] info = { name, phone, password, reenterPass, gradMonth, gradYear };

//		for(String i : info) {
//			System.out.println(i);
//		}

		Failures result = UserService.getInstance().update(info);

		switch (result) {
		case emptyField:
			JOptionPane.showMessageDialog(null, "Fields must not be empty, please fill in all fields.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidName:
			JOptionPane.showMessageDialog(null, "Invalid name. Enter first and last name.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case emailInUse:
			JOptionPane.showMessageDialog(null, "Email is already in use.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidPhoneNumber:
			JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidPasswordStandard:
			JOptionPane.showMessageDialog(null,
					"Password must contain >8 characters, at least 1 uppercase, at least 1 lowercase,at least 1 number, "
							+ "and 1 special symbol. Try again.",
					"Create Account", JOptionPane.INFORMATION_MESSAGE);
			break;
		case passwordMismatch:
			JOptionPane.showMessageDialog(null, "Your passwords do not match! Try again.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case invalidGraduationDate:
			JOptionPane.showMessageDialog(null, "Invalid graduation month/year.", "Create Account",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			break;

		}

		return result == Failures.SUCCESS;

//		if (name == null || name.length() == 0 || phone == null || phone.length() == 0 || password == null
//				|| password.length() == 0 || reenterPass == null || reenterPass.length() == 0) {
//			JOptionPane.showMessageDialog(null, "Fields must not be empty, please fill in all fields.",
//					"Create Account", JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate name must be first and last
//		String[] username = name.split(" ");
//		if (username.length != 2 || username[0].equals(username[1])) {
//			JOptionPane.showMessageDialog(null, "Invalid name. Enter first and last name.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate phone
//		if (phone.length() != 10) {
//			JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10 digits.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// validate password with regex hopefully it works
//		Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[!@#$%])(?=.*[A-Z]).{8,})");
//
//		Matcher m = p.matcher(password);
//		boolean matches = m.matches();
//
//		if (!matches) {
//			JOptionPane.showMessageDialog(null,
//					"Password must contain >8 characters, at least 1 uppercase, at least 1 lowercase,at least 1 number, "
//							+ "and 1 special symbol. Try again.",
//					"Create Account", JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		// passwords must match
//		if (!(password.equals(reenterPass))) {
//			JOptionPane.showMessageDialog(null, "Your passwords do not match! Try again.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		Integer year = Calendar.getInstance().get(Calendar.YEAR);
//		Integer month = Calendar.getInstance().get(Calendar.MONTH);
//		Integer gradMonthSelect = Integer.parseInt(gradMonth);
//		Integer gradYearSelect = Integer.parseInt(gradYear);
//
//		if (gradMonthSelect < month && gradYearSelect == year) {
//			JOptionPane.showMessageDialog(null, "Invalid graduation month/year.", "Create Account",
//					JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//
//		return true;
	}
}