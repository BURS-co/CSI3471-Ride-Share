package data.user;

import data.survey.Survey;

public class Admin extends User {

	public Admin() {
		super();
	}

	public void notifyAdmin(Report r) {

	}

	public void storeReport(Report r) {

	}

	public void storeSurvey(Survey s) {

	}

	@Override
	public String toString() {
		return username + " " + email + " " + phoneNumber + " " + password + " true\n";
	}

}
