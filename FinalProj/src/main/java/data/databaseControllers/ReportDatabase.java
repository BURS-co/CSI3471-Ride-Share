/**
 * @author Joseph Yu, Joshua Huertas
 */
package data.databaseControllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import data.user.Report;

public class ReportDatabase extends AbstractDatabase implements IWrite{
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

	public void load() throws ParseException, IOException {
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File("../src/main/resources/reportDatabase.txt")));

			String line = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split(",,,");
				Report r = new Report();

				// supply s with info
				r.setReportee(split[0]);
				r.setReporter(split[1]);
				r.setReason(split[2]);

				// Add data
				reportData.add(r);
			}
			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write() throws IOException {
		// open file
		BufferedWriter write = new BufferedWriter(new FileWriter("../src/main/resources/reportDatabase.txt"));

		for (Report s : reportData) {
			write.write(s.toString());
		}

		write.flush();
		write.close();
	}

	public static void addReport(Report s) {
		reportData.add(s);
	}

	public static ArrayList<Report> getReportData() {
		return reportData;
	}

	@Override
	public ArrayList<Report> getData() {
		return reportData;
	}

	@Override
	public String getDelimiter() {
		return ",,,";
	}

	@Override
	public void add(Object item) {
		reportData.add((Report) item);		
	}

	@Override
	public Object makeItem(String[] split) {
		Report r = new Report();

		// supply s with info
		r.setReportee(split[0]);
		r.setReporter(split[1]);
		r.setReason(split[2]);
		
		return (Object)r;
	}
}
