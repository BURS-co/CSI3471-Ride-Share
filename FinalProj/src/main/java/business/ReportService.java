package business;

import java.util.concurrent.locks.ReentrantLock;

import data.survey.Survey;
import data.user.Report;
import enums.Failures;

public class ReportService {
	// singleton
		private static ReportService reportService = null;
		private static ReentrantLock lock = new ReentrantLock();
		//private SurveyDatabase database;

		private ReportService() {
			//database = SurveyDatabase.getInstance();
		}

		public static ReportService getInstance() {
			if (reportService == null) {
				lock.lock();
				if (reportService == null)
					reportService = new ReportService();
			}

			return reportService;
		}
		
		public Failures verifyReport(String[] list) {
			return null;
		}
		
		private Report createReport(String[] list) {
			return null;
		}
		
		public void storeReport(String[] list) {
			
		}
}
