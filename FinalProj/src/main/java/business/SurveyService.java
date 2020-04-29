package business;

import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.SurveyDatabase;
import data.survey.Survey;

public class SurveyService {

	// singleton
	private static SurveyService surveyService = null;
	private static ReentrantLock lock = new ReentrantLock();
	//private SurveyDatabase database;

	private SurveyService() {
		//database = SurveyDatabase.getInstance();
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
//		try {
//			
//		} catch {
//			
//		}

		// store survey if it was successfully validated
		if (result) {
			
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
}
