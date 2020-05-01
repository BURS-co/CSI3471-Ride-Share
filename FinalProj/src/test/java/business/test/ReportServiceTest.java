package business.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import business.ReportService;
import data.user.Report;
import enums.Failures;

public class ReportServiceTest {
	@Test
	public void testVerify() {
		Failures result;

		result = ReportService.getInstance().verify(new String[] { "", "", "" });

		assertEquals(result, Failures.emptyField);

		result = ReportService.getInstance().verify(new String[] {});

		assertEquals(result, Failures.emptyField);

		result = ReportService.getInstance().verify(new String[] { "andrew_ammentorp1@baylor.edu",
				"joseph_perez3@baylor.edu", "This guy can't stay in his lane" });

		assertEquals(result, Failures.SUCCESS);

	}

	@Test
	public void testCreate() {
		String list[] = { "andrew_ammentorp1@baylor.edu", "joseph_perez3@baylor.edu",
				"This guy can't stay in his lane" };
		Report r = ReportService.getInstance().create(list);

		assertEquals(r.getReporter(), list[0]);
		assertEquals(r.getReportee(), list[1]);
		assertEquals(r.getReason(), list[2]);
	}
}
