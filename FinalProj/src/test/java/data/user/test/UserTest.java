package data.user.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.user.User;

public class UserTest {
	@Test
	public void testUsername() {
		User u = new User();

		u.setUsername("Andrew Ammentorp");

		assertEquals("Andrew Ammentorp", u.getUsername());
	}

	@Test
	public void testPassword() {
		User u = new User();
		String s = "Password123!";
		u.setPassword(s);

		assertEquals(s, u.getPassword());
	}

	@Test
	public void testEmail() {
		User u = new User();
		String s = "andrew_ammentorp1@baylor.edu";
		u.setEmail(s);

		assertEquals(s, u.getEmail());
	}

	@Test
	public void testPhone() {
		User u = new User();
		String s = "1234567890";
		u.setPhoneNumber(s);

		assertEquals(s, u.getPhoneNumber());
	}

	@Test
	public void testGradMonth() {
		User u = new User();
		String s = "5";
		u.setGradMonth(s);

		assertEquals(s, u.getGradMonth());
	}

	@Test
	public void testGradYear() {
		User u = new User();
		String s = "2020";
		u.setGradYear(s);

		assertEquals(s, u.getGradYear());
	}

	@Test
	public void testJoinNotif() {
		User u = new User();

		u.setJoinNotif(true);

		assertTrue(u.getJoinNotif());

		u.setJoinNotif(false);

		assertFalse(u.getJoinNotif());
	}
	@Test
	public void testCancel() {
		User u = new User();

		u.setPostCanceledNotif(true);

		assertTrue(u.getPostCanceledNotif());

		u.setPostCanceledNotif(false);

		assertFalse(u.getPostCanceledNotif());
	}
	@Test
	public void testEquals() {
		User u1 = new User();
		User u2 = new User();
		String s = "Andrew Ammentorp";
		u1.setUsername(s);
		u2.setUsername(s);
		
		s = "Password123!";
		u1.setPassword(s);
		u2.setPassword(s);
		
		s = "andrew_ammentorp1@baylor.edu";
		u1.setEmail(s);
		u2.setEmail(s);
		
		s = "0123456789";
		u1.setPhoneNumber(s);
		u2.setPhoneNumber(s);
		
		s = "5";
		u1.setGradMonth(s);
		u2.setGradMonth(s);
		
		s = "2022";
		u1.setGradYear(s);
		u2.setGradYear(s);
		
		u1.setJoinNotif(true);
		u2.setJoinNotif(true);
		
		u1.setPostCanceledNotif(true);
		u2.setPostCanceledNotif(true);
		
		assertEquals(u1.toString(),u2.toString());
		assertTrue(u1.equals(u2));
		
		u1.setUsername("Diff Name");
		
		assertNotEquals(u1.toString(),u2.toString());
		assertFalse(u1.equals(u2));
	}
}
