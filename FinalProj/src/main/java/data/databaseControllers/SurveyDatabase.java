package data.databaseControllers;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import data.survey.Survey;

public class SurveyDatabase {
	//singleton
	private static SurveyDatabase surveyDatabase = null;// new UserDatabase();
	private static ReentrantLock lock = new ReentrantLock();
	
	private SurveyDatabase() {}

	public static SurveyDatabase getInstance() {
		if(surveyDatabase == null) {
			lock.lock();
			if(surveyDatabase == null)
				surveyDatabase = new SurveyDatabase();
		}
		
			
		return surveyDatabase;
	}
	
	private static ArrayList<Survey> surveyData = new ArrayList<Survey>();
	
	public static void load() {
		
	}
	
	public static void write() {
		
	}
	
	public static void add(Survey s) {
		surveyData.add(s);
	}
	
	public static ArrayList<Survey> getUserData(){
		return surveyData;
	}
	
}
