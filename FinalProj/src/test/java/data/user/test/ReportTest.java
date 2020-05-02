package data.user.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.user.Report;

public class ReportTest {
	@Test
	public void testReportee() {
		Report r = new Report();
		r.setReason("Offensive email");
		r.setReportee("Pinhead");
		r.setReporter("Dirty Dan");
		
		assertEquals(r.getReportee(),"Pinhead");
	}
	@Test
	public void testReporter() {
		Report r = new Report();
		r.setReason("Offensive email");
		r.setReportee("Pinhead");
		r.setReporter("Dirty Dan");
		
		assertEquals(r.getReporter(),"Dirty Dan");
	}
	@Test
	public void testReason() {
		Report r = new Report();
		r.setReason("Offensive email");
		r.setReportee("Pinhead");
		r.setReporter("Dirty Dan");
		
		
		assertEquals(r.getReason(),"Offensive email");
	}
	@Test
	public void testToString() {
		Report r = new Report();
		r.setReason("Offensive email");
		r.setReportee("Pinhead");
		r.setReporter("Dirty Dan");
		
		Report r2 = new Report();
		r2.setReason("Offensive email");
		r2.setReportee("Pinhead");
		r2.setReporter("Dirty Dan");
		
		assertEquals(r.toString(),r2.toString());
		
		r2.setReason("Offensive name");
		assertNotEquals(r.toString(),r2.toString());
	}
}
