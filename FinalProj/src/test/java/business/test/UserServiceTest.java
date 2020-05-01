package business.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import business.UserService;
import data.user.User;
import enums.Failures;

public class UserServiceTest {
	@Test
	public void testVerify() {
		Failures result;

		result = UserService.getInstance().verify(new String[] {});

		assertEquals(result, Failures.emptyField);

		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "password", "05", "" });//empty field at end

		assertEquals(result, Failures.emptyField);
		
		result = UserService.getInstance().verify(new String[] { "AndrewAmmentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidName);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Andrew", "andrew_ammentorp@baylor.edu",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidName);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@gmail.com",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidEmail);
		
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidPhoneNumber);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "password", "password", "05","2022" });

		assertEquals(result, Failures.invalidPasswordStandard);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "Password123!", "password", "05","2022" });

		assertEquals(result, Failures.passwordMismatch);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "Password123!", "Password123!", "01","2020" });

		assertEquals(result, Failures.invalidGraduationDate);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "andrew_ammentorp@baylor.edu",
				"0123456789", "Password123!", "Password123!", "05","2022" });

		assertEquals(result, Failures.SUCCESS);
		
	}

	@Test
	public void testCreate() {
		String list[] = { "Andrew Ammentorp", "andrew_ammentorp1@baylor.edu", "0123456789", "password", "05", "2022" };
		User test1 = new User();

		test1.setUsername(list[0]);
		test1.setEmail(list[1]);
		test1.setPhoneNumber(list[2]);
		test1.setPassword(list[3]);
		test1.setGradMonth(list[4]);
		test1.setGradYear(list[5]);

		User test2 = UserService.getInstance().create(list);

		assertEquals(test1.toString(), test2.toString());

		test1.setPassword("different password");
		assertNotEquals(test1.toString(), test2.toString());
	}

	@Test
	public void testUpdate() {
		Failures result;

		result = UserService.getInstance().verify(new String[] {});

		assertEquals(result, Failures.emptyField);

		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"0123456789", "password", "05", "" });//empty field at end

		assertEquals(result, Failures.emptyField);
		
		result = UserService.getInstance().verify(new String[] { "AndrewAmmentorp", "bruh@baylor.edu",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidName);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Andrew", "bruh@baylor.edu",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidName);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@gmail.com",
				"0123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidEmail);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"123456789", "password", "05", "2022" });

		assertEquals(result, Failures.invalidPhoneNumber);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"0123456789", "password", "password", "05","2022" });

		assertEquals(result, Failures.invalidPasswordStandard);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"0123456789", "Password123!", "password", "05","2022" });

		assertEquals(result, Failures.passwordMismatch);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"0123456789", "Password123!", "Password123!", "01","2020" });

		assertEquals(result, Failures.invalidGraduationDate);
		
		result = UserService.getInstance().verify(new String[] { "Andrew Ammentorp", "bruh@baylor.edu",
				"0123456789", "Password123!", "Password123!", "05","2022" });

		assertEquals(result, Failures.SUCCESS);
	}

}
