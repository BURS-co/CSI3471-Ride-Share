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
import java.util.concurrent.locks.ReentrantLock;

import data.survey.Survey;

public class SurveyDatabase {
	// singleton
	private static SurveyDatabase surveyDatabase = null;
	private static ReentrantLock lock = new ReentrantLock();

	private SurveyDatabase() {
	}

	public static SurveyDatabase getInstance() {
		if (surveyDatabase == null) {
			lock.lock();
			if (surveyDatabase == null)
				surveyDatabase = new SurveyDatabase();
		}

		return surveyDatabase;
	}

	private static ArrayList<Survey> surveyData = new ArrayList<Survey>();

	public void load() throws ParseException, IOException {
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File("surveyDatabase.txt")));

			String line = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split(",,,");
				Survey s = new Survey();

				// supply s with info
				s.setName(split[0]);
				s.setTarget(split[1]);
				s.setRating(Integer.valueOf(split[2]));
				s.setComments(split[3]);

				// Add data
				surveyData.add(s);
			}
			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write() throws IOException {
		// open file
		BufferedWriter write = new BufferedWriter(new FileWriter("surveyDatabase.txt"));

		for (Survey s : surveyData) {
			write.write(s.toString());
		}

		write.flush();
		write.close();
	}

	public static void addSurvey(Survey s) {
		surveyData.add(s);
	}

	public static ArrayList<Survey> getUserData() {
		return surveyData;
	}
}
