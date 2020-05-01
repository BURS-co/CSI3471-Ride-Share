package data.databaseControllers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.databaseControllers.UserDatabase;

public class UserDatabaseTest {
	@Test
	public void testGetInstance() {
		//testing singleton
		UserDatabase a = UserDatabase.getInstance();
		UserDatabase b = UserDatabase.getInstance();
		
		assertEquals(a,b);
		assertSame(a,b);
	}
}
