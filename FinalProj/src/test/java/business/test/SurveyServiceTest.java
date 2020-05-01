package business.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import business.SurveyService;
import data.survey.Survey;
import data.user.Report;
import enums.Failures;

public class SurveyServiceTest {
	@Test
	public void testVerify() throws FileNotFoundException {
		Failures result;
		

		result = SurveyService.getInstance().verify(new String[] { "", "", "" , ""});

		assertEquals(result, Failures.emptyField);

		result = SurveyService.getInstance().verify(new String[] {});

		assertEquals(result, Failures.emptyField);

		/*result = SurveyService.getInstance().verify(new String[] { "andrew_ammentorp1@baylor.edu",
				"joseph_perez3@baylor.edu", "0", "This guy can't stay in his lane" });
		
		assertEquals(result, Failures.SUCCESS);*/

		
	}

	@Test
	public void testCreate() {
		String list[] = { "andrew_ammentorp1@baylor.edu", "joseph_perez3@baylor.edu", "0",
				"This guy can't stay in his lane" };
		Survey s = SurveyService.getInstance().create(list);

		assertEquals(s.getName(), list[0]);
		assertEquals(s.getTarget(), list[1]);
		assertEquals((Integer)s.getRating(), Integer.valueOf(list[2]));
		assertEquals(s.getComments(), list[3]);
		
	}
}
