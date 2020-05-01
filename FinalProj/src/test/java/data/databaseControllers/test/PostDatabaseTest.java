package data.databaseControllers.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.databaseControllers.PostDatabase;

public class PostDatabaseTest {
	@Test
	public void testGetInstance() {
		//testing singleton
		PostDatabase a = PostDatabase.getInstance();
		PostDatabase b = PostDatabase.getInstance();
		
		assertEquals(a,b);
		assertSame(a,b);
	}
}
