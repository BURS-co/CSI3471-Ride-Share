package business;

import java.util.concurrent.locks.ReentrantLock;

import data.survey.Survey;
import data.user.Report;
import enums.Failures;

public class ReportService implements IService{
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

		@Override
		public Failures verify(String[] list) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void store(String[] list) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object create(String[] list) {
			// TODO Auto-generated method stub
			return null;
		}
}
