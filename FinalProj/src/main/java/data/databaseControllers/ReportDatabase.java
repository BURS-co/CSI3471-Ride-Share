package data.databaseControllers;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import data.user.Report;

public class ReportDatabase {
	// singleton
		private static ReportDatabase reportDatabase = null;
		private static ReentrantLock lock = new ReentrantLock();

		private ReportDatabase() {
		}

		public static ReportDatabase getInstance() {
			if (reportDatabase == null) {
				lock.lock();
				if (reportDatabase == null)
					reportDatabase = new ReportDatabase();
			}

			return reportDatabase;
		}

		private static ArrayList<Report> reportData = new ArrayList<Report>();
		
		public void load() {
			
		}
		
		public static void write() {
			
		}
		
		public static void addReport(Report s) {
			
		}
		
		public static ArrayList<Report> getReportData(){
			return reportData;
		}
}
