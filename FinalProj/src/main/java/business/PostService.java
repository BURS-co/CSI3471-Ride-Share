package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.PostDatabase;
import data.post.Post;
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

		if (input.length != 0) {
			for (String field : input) {
				if (field.isEmpty()) {
					// result = false;
					result = Failures.emptyField;
					break;
				}
			}
		} else {
			// result = false;
			result = Failures.emptyField;
		}

		if (result == Failures.emptyField) {
			return result;
		}

		// process date string?
		// or process date object if its been made by presentation layer

		Date today = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a");

		String todayDay = f.format(today);
		Date todaysDay;
		String dayTime;
		Date inputDate;
		int comp = 0;
		
		try {
			todaysDay = f.parse(todayDay);
			dayTime = input[2] + " " + input[3] + " " + input[4] + " " + input[5] + ":" + input[6] + " "
					+ input[7];
			inputDate = f.parse(dayTime);
			comp = todaysDay.compareTo(inputDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (input[0].equals(input[1])) {
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
