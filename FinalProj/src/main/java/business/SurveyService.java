package business;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.SurveyDatabase;
import data.survey.Survey;
import enums.Failures;

public class SurveyService implements IService {

	// singleton
	private static SurveyService surveyService = null;
	private static ReentrantLock lock = new ReentrantLock();
	// private SurveyDatabase database;

	private SurveyService() {
		// database = SurveyDatabase.getInstance();
	}

	public static SurveyService getInstance() {
		if (surveyService == null) {
			lock.lock();
			if (surveyService == null)
				surveyService = new SurveyService();
		}

		return surveyService;
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
		try {
			Integer.valueOf(list[2]);
		} catch (Exception e) {
			// result = false;
			result = Failures.SurveyField2notANumber;
		}

		if (list[3].length() > 300) {
			// result = false;
			result = Failures.SurveyField3TooLong;
		}

		// store survey if it was successfully validated
		if (result == Failures.SUCCESS) {

			store(list);
		}
		return result;
	}

	public Survey create(String[] list) {
		// create the survey
		Survey surv = new Survey();

		surv.setName(list[0]);
		surv.setTarget(list[1]);
		surv.setRating(Integer.valueOf(list[2]));
		surv.setComments(list[3]);

		return surv;
	}

	public void store(String[] list) {
		SurveyDatabase.addSurvey(create(list));
		try {
			SurveyDatabase.getInstance().write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
