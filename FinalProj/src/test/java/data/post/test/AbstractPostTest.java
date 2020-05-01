package data.post.test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import data.post.AbstractPost;
import data.post.Driver;

public class AbstractPostTest {
	@Test
	public void testId() {
		AbstractPost p = new Driver("1");
		
		assertEquals(p.getID(),1);
	}
	@Test
	public void testPoster() {
		AbstractPost p = new Driver("1");
		
		p.setPoster("andrew_ammentorp1@baylor.edu");
		assertEquals("andrew_ammentorp1@baylor.edu",p.getPoster());
	}
	@Test
	public void testOrigin() {
		AbstractPost p = new Driver("1");
		
		p.setOrigin("HOU");
		assertEquals("HOU",p.getOrigin());
	}
	@Test
	public void testDest() {
		AbstractPost p = new Driver("1");
		p.setDest("HOU");
		assertEquals("HOU",p.getDest());
	}
	@Test
	public void testDate() {
		AbstractPost p = new Driver("1");
		Date d = new Date();
		p.setDate(d);
		assertEquals(d,p.getDate());
	}
	@Test
	public void testExpired() throws InterruptedException {
		AbstractPost p = new Driver("1");
		Date d = new Date();
		p.setDate(d);
		Thread.sleep(1000);
		assertTrue(p.isExpired());
		
		d.setYear(2021);
		p.setDate(d);
		assertFalse(p.isExpired());
		
	}
	
}
