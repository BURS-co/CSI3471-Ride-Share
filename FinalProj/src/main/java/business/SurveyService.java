package business;

import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.SurveyDatabase;
import data.survey.Survey;

public class SurveyService {

	// singleton
	private static SurveyService surveyService = null;
	private static ReentrantLock lock = new ReentrantLock();
	private SurveyDatabase database;

	private SurveyService() {
		database = SurveyDatabase.getInstance();
	}

	public static SurveyService getInstance() {
		if (surveyService == null) {
			lock.lock();
			if (surveyService == null)
				surveyService = new SurveyService();
		}

		return surveyService;
	}

	public boolean verifySurvey(String[] list) {
		boolean result = true;
		if (list.length != 0) {
			for (String field : list) {
				if (field.isEmpty()) {
					result = false;
					break;
				}
			}
		} else {
			result = false;
		}

		// more validation tests...

		// store survey if it was successfully validated
		if (result) {
			
			storeSurvey(createSurvey(list));
		}
		return result;
	}

	private Survey createSurvey(String[] list) {
		// TODO Auto-generated method stub
		return null;
	}

	public void storeSurvey(Survey s) {
		// should call the survay database storeSurvey()
		//SurveyDatabase database = SurveyDatabase.getInstance();
		
		
	}
}
