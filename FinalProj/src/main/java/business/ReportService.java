package business;

import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.ReportDatabase;
import data.user.Report;
import enums.Failures;

public class ReportService implements IService {
	// singleton
	private static ReportService reportService = null;
	private static ReentrantLock lock = new ReentrantLock();
	// private SurveyDatabase database;

	private ReportService() {
		// database = SurveyDatabase.getInstance();
	}

	public static ReportService getInstance() {
		if (reportService == null) {
			lock.lock();
			if (reportService == null)
				reportService = new ReportService();
		}

		return reportService;
	}

	// @Override
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

		// store survey if it was successfully validated
		if (result == Failures.SUCCESS) {

			store(list);
		}
		return result;
	}

	// @Override
	public void store(String[] list) {
		ReportDatabase.addReport(create(list));

	}

	// @Override
	public Report create(String[] list) {
		// create the report
		Report rep = new Report();

		rep.setReporter(list[0]);
		rep.setReportee(list[1]);
		rep.setReason(list[2]);

		return rep;
	}
}
