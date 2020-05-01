package business.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import business.PostService;
import data.post.AbstractPost;
import data.user.User;
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
	
	@Test
	public void testAddProspects() {
		User u = new User();
		u.setEmail("andrew_ammentorp1@baylor.edu");
		u.setGradMonth("05");
		u.setGradYear("2022");
		u.setPassword("Andrew8726!");
		u.setPhoneNumber("0123456789");
		u.setUsername("andrew ammentorp");
		
		Failures result = PostService.getInstance().addProspects(u, 1);
		
		assertEquals(result,Failures.noMatchingQuery);
	}
	
	@Test
	public void testCreate() {
		
		assertThrows(NullPointerException.class , () -> {AbstractPost p = null;p = PostService.getInstance().create(null); });
	}
}
