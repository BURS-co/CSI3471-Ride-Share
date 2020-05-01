package business.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import business.CreatePostValidate;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CreatePostValidateTest {
	@Test
	public void testRiderPostValidate() throws ParseException {
		//Valid post
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "AUS", "01", "JUL", "2020", "1", "30", "PM", null),true);
		//Make sure fails same airport
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "HOU", "01", "JUL", "2020", "1", "30", "PM", null),false);	
		//Make sure returns false on bad date
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "DAL", "01", "JAN", "2020", "1", "30", "PM", null),false);	
	}
	@Test
	public void testDriverPostValidate() throws ParseException {
		//Valid post
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "AUS", "01", "JUL", "2020", "1", "30", "PM", 5, null),true);
		//Make sure fails same airport
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "HOU", "01", "JUL", "2020", "1", "30", "PM", 6, null),false);	
		//Make sure returns false on bad date
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "DAL", "01", "JAN", "2020", "1", "30", "PM", 2, null),false);	
		//Make sure returns false on bad capacity
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "DAL", "01", "JUL", "2020", "1", "30", "PM", 0, null),false);
		//Make sure returns false on bad capacity
		assertEquals(CreatePostValidate.validatePostInfo("HOU", "DAL", "01", "JUL", "2020", "1", "30", "PM", -1, null),false);
	}
}
