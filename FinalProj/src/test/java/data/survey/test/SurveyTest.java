package data.survey.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import data.survey.Survey;

public class SurveyTest {
	@Test
	public void testName() {
		Survey s = new Survey("Andrew Ammentorp", "Driver", "This guy was trash", 1);

		assertEquals("Andrew Ammentorp", s.getName());
	}

	@Test
	public void testTarget() {
		Survey s = new Survey("Andrew Ammentorp", "Driver", "This guy was trash", 1);

		assertEquals("Driver", s.getTarget());
	}

	@Test
	public void testComments() {
		Survey s = new Survey("Andrew Ammentorp", "Driver", "This guy was trash", 1);

		assertEquals("This guy was trash", s.getComments());
	}

	@Test
	public void testRating() {
		Survey s = new Survey("Andrew Ammentorp", "Driver", "This guy was trash", 1);

		assertEquals((Integer)1, (Integer)s.getRating());
	}
}
