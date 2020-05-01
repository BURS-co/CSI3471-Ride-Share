package data.databaseControllers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.databaseControllers.ReportDatabase;

public class ReportDatabaseTest {
	@Test
	public void testGetInstance() {
		//testing singleton
		ReportDatabase a = ReportDatabase.getInstance();
		ReportDatabase b = ReportDatabase.getInstance();
		
		assertEquals(a,b);
		assertSame(a,b);
	}
}
