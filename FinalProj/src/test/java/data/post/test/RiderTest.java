package data.post.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.post.Rider;
import data.post.Prospects;
public class RiderTest {
	@Test
	public void testDriver() {
		//new rider made
		Rider r = new Rider("1");
		
		Prospects p = new Prospects();
		p.setName("Andrew Ammentorp");
		p.setStatus(true);
		
		r.setDriver(p);
		
		assertEquals(r.getDriver(),p);
	}
}
