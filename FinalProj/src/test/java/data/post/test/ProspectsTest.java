package data.post.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import data.post.Prospects;
import data.post.Rider;
public class ProspectsTest {
	@Test
	public void testStatus() {
		Prospects p = new Prospects();
		
		p.setStatus(true);
		assertTrue(p.getStatus());
		
		p.setStatus(false);
		assertFalse(p.getStatus());
	}
	@Test
	public void testName() {
		Prospects p = new Prospects();
		
		p.setName("Andrew Ammentorp");
		
		assertEquals("Andrew Ammentorp",p.getName());
		
		p.setName("Sir Bruh the III ");
		
		assertEquals("Sir Bruh the III ",p.getName());
	}
}
