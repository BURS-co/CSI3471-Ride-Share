package data.user.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.user.Admin;

public class AdminTest {
	@Test
	public void testToString() {
		Admin a = new Admin();
		//test if gets true for being an admin
		assertEquals(a.toString(),"null" + ",,," + "null" + ",,," + "null" + ",,," + "null" + ",,," + "null" + ",,," + "null"
				+ ",,,true,,," + "false" + ",,," + "false" + "\n");
		
	}
}
