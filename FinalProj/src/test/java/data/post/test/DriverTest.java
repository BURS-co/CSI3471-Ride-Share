package data.post.test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import data.post.Driver;
import data.post.Prospects;
public class DriverTest {
	@Test
	public void testRiderLimit() {
		Driver d = new Driver("1");
		
		d.setRiderLimit(5);
		
		assertEquals((Integer)5,d.getRiderLimit());
	}
	@Test
	public void testProspects() {
		Driver d = new Driver("1");
		//comment pt2
		Prospects p = new Prospects();
		p.setName("Andrew Ammentorp");
		p.setStatus(true);
		
		d.addRiders(p);
		//make sure spot is same
		assertEquals(d.getRiders().get(0),p);
		
		ArrayList<Prospects> ap = new ArrayList<Prospects>();
		ap.add(p);
		
		assertEquals(d.getRiders(),ap);
	}
}