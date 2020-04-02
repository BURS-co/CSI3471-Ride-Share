package data.databaseControllers;

import java.util.ArrayList;
import data.survey.Survey;

public class SurveyDatabase {
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
