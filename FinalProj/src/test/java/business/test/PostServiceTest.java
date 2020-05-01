package business.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import business.PostService;
import enums.Failures;

public class PostServiceTest {
	@Test
	public void testVerify() {
		Failures result = PostService.getInstance().verify(null);
		// no string
		assertEquals(result, Failures.emptyField);
		// empty field
		result = PostService.getInstance().verify(new String[] { "HOU", "", "JUL", "1", "2020", "1", "00", "PM" });

		assertEquals(result, Failures.emptyField);
		// same dest
		result = PostService.getInstance().verify(new String[] { "HOU", "HOU", "JUL", "1", "2020", "1", "00", "PM" });

		assertEquals(result, Failures.SameOriginandDestination);
		// Bad date
		result = PostService.getInstance().verify(new String[] { "HOU", "DAL", "JAN", "1", "2020", "1", "00", "PM" });

		assertEquals(result, Failures.BadDate);

		// Bad number
		result = PostService.getInstance()
				.verify(new String[] { "HOU", "DAL", "JUL", "1", "2020", "1", "00", "PM", "100" });

		assertEquals(result, Failures.PostField8NotInRange);

		result = PostService.getInstance()
				.verify(new String[] { "HOU", "DAL", "JUL", "1", "2020", "1", "00", "PM", "-1" });

		assertEquals(result, Failures.PostField8NotInRange);

		// Not a number
		result = PostService.getInstance()
				.verify(new String[] { "HOU", "DAL", "JUL", "1", "2020", "1", "00", "PM", "Pizza Time" });

		assertEquals(result, Failures.PostField8notANumber);
		
		/** Couldn't assert normal posts because of no current user **/

	}
}
