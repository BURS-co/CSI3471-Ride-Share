package data.user;

import data.survey.Survey;

/**
 * @author Andrew Ammentorp, Class for an Admin, a subset of User
 */
public class Admin extends User {

	/**
	 * Default constructor for an Admin
	 */
	public Admin() {
		super();
	}

	/**
	 * Notifies the admin of a report
	 * 
	 * @param r the report for the Admin to be notified about
	 */
	public void notifyAdmin(Report r) {

	}

	/**
	 * Stores a report
	 * 
	 * @param r the report to be stored by the Admin
	 */
	public void storeReport(Report r) {

	}

	/**
	 * Stores a survey
	 * 
	 * @param s the survey to be stored by the Admin
	 */
	public void storeSurvey(Survey s) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data.user.User#toString()
	 */
	@Override
	public String toString() {
		return username + ",,," + email + ",,," + phoneNumber + ",,," + gradMonth + ",,," + gradYear + ",,," + password
				+ ",,,true\n";
	}

}
