package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import data.databaseControllers.PostDatabase;
import data.post.AbstractPost;
import data.post.Driver;
import data.post.Prospects;
import data.post.Rider;
import data.user.User;
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

		if (input[0].equals(input[1])) {
			result = Failures.SameOriginandDestination;
		}

		if (input.length == 9) {
			try {
				int temp = Integer.valueOf(input[8]);

				if (!(temp > 0 && temp < 99)) {
					result = Failures.PostField8NotInRange;
				}
			} catch (Exception e) {
				result = Failures.PostField8notANumber;
			}
		}

		if (result != Failures.SUCCESS) {
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
			dayTime = input[3] + " " + input[2] + " " + input[4] + " " + input[5] + ":" + input[6] + " " + input[7];
			inputDate = f.parse(dayTime);
			comp = todaysDay.compareTo(inputDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Failures addProspects(User u, int ID) {
		Failures result = Failures.SUCCESS;
		Driver p = (Driver) PostDatabase.searchDatabase(ID);
		if (p == null) {
			result = Failures.noMatchingQuery;
			return result;
		}

		Integer limit = p.getRiderLimit();
		int current_passengers = p.getRiders().size();

		if (current_passengers < limit) {
			Prospects passenger = new Prospects(u.getUsername());
			passenger.setStatus(false);
			p.addRiders(passenger);
		} else {
			result = Failures.reachMaxCapacity;
		}
		return result;
	}

	// @Override
	public void store(String[] list) {
		PostDatabase.addPost(create(list));
	}

	// @Override
	public AbstractPost create(String[] list) {
		// create the report
		AbstractPost p = null;
		if (list.length == 8) {
			p = new Rider(list);
		} else {
			p = new Driver(list);
		}
		return p;
	}
}
