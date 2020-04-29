package data.databaseControllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import data.survey.Survey;
import data.user.User;

public class SurveyDatabase {
	// singleton
	private static SurveyDatabase surveyDatabase = null;// new UserDatabase();
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

	public static void load() {
        
	}

	public static void write() throws IOException {
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
