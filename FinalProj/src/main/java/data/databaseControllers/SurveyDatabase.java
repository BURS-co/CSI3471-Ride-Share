package data.databaseControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import data.post.DriverPost;
import data.post.Post;
import data.survey.Survey;
//bruh
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

	public void load() throws ParseException, IOException{
		try {
			BufferedReader loader = new BufferedReader(new FileReader(new File("surveyDatabase.txt")));

			String line = null;

			while ((line = loader.readLine()) != null) {

				String[] split = line.split("-");
				Survey s = null;
				for (int i = 0; i < split.length; i++) {
					 //supply s with info
				}
				// Add data
				surveyData.add(s);
			}
			loader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void write() {

	}

	public static void addSurvey(Survey s) {
		surveyData.add(s);
	}

	public static ArrayList<Survey> getUserData() {
		return surveyData;
	}
}
