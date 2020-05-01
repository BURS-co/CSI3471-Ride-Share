package business.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

import business.Login;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class LoginTest {
	@SuppressWarnings("unused")
	@Test
	public void testAuthenticate() {
		//correct login info
		assertNotNull(Login.authenticate("andrew_ammentorp1@baylor.edu", "Andrew8726!"));
		
		//incorrect login - email
		assertFalse(Login.authenticate("andrew_bruh@baylor.edu", "Andrew8726!"));
		//incorrect login - password
		assertFalse(Login.authenticate("andrew_ammentorp1@baylor.edu", "bruh"));
		
		
	}
}
