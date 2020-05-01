package data.databaseControllers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.databaseControllers.SurveyDatabase;

public class SurveyDatabaseTest {
	@Test
	public void testGetInstance() {
		//testing singleton
		SurveyDatabase a = SurveyDatabase.getInstance();
		SurveyDatabase b = SurveyDatabase.getInstance();
		
		assertEquals(a,b);
		assertSame(a,b);
	}
}