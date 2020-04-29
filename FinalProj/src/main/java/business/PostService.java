package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.ReportDatabase;
import data.post.Post;
import data.user.Report;
import enums.Failures;

public class PostService implements IService {
	// singleton
	private static PostService postService = null;
	private static ReentrantLock lock = new ReentrantLock();
	// private SurveyDatabase database;

	private PostService() {
		// database = SurveyDatabase.getInstance();
	}

	public static PostService getInstance() {
		if (postService == null) {
			lock.lock();
			if (postService == null)
				postService = new PostService();
		}

		return postService;
	}

	public Failures verify(String[] input) {

		Failures result = Failures.SUCCESS;

		if (input.length == 0) {
			result = Failures.emptyField;
		}

		for (String i : input) {
			if (i.length() == 0) {
				result = Failures.emptyField;
			}
		}

		// process date string?
		// or process date object if its been made by presentation layer

		Date today = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a");
		
		String todayDay = f.format(today);
		Date todaysDay = f.parse(todayDay);
		
		String dayTime = d + " " + m + " " + y + " " + h + ":" + min + " " + tOd;
		Date inputDate = f.parse(dayTime);
		int comp = 0;
		comp = todaysDay.compareTo(inputDate);
		

		if (o.equals(dest)) {
			result = Failures.SameOriginandDestination;
		}
		if (comp > 0) {
			result = Failures.BadDate;
		}
		
		// Store post if validation is successful
		if (result == Failures.SUCCESS) {

			store(input);
		}

		return result;
	}

//	public boolean Validate(String[] input, int capacity) {
//		if(capacity < 1 || capacity > 99) {
//			return false;
//		}
//		
//		return Validate(input);
//	}

//	public void Store(String[] input, boolean driver) {
//
//		if (driver) {
//			// create driver post
//		} else {
//			// create rider post
//		}
//
//		// should this create a post object to store in the database?
//	}

	@Override
	public void store(String[] list) {
		PostDatabase.addPost(create(list));

	}

	@Override
	public Post create(String[] list) {
		// create the report
		Post p = new Post();

		return p;
	}
}
