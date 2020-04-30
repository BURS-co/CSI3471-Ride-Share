/**
 * @author Joseph Yu, Joshua Huertas
 */
package business;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
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
	
	public void delete(int ID) {
		int index = PostDatabase.getInstance().searchDatabaseInt(ID);
		PostDatabase.getInstance().getPostData().remove(index);
	}

	public Failures addProspects(User u, int ID) {
		Failures result = Failures.SUCCESS;
		Driver p = (Driver) PostDatabase.getInstance().searchDatabase(ID);
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
		PostDatabase.getInstance().addPost(create(list));
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
	
	public void joinRider(String postID) {
		
		int temp = Integer.valueOf(postID);
		Driver d = ((Driver) PostDatabase.getInstance().searchDatabase(temp));

		Prospects rider = new Prospects();
		rider.setName(UserService.getInstance().getCurrentUser().getUsername());
		rider.setStatus(false);

		if(d.getRiders() == null) {
			d.setRiders(new ArrayList<Prospects>());
		}
		
		if (d.getRiderLimit() > d.getRiders().size()) {

			ArrayList<Prospects> riders = d.getRiders();
			riders.add(rider);
			d.setRiders(riders);

			PostDatabase.getInstance().storeUpdate(d);

			UserDatabase.getInstance().queryDatabase(d.getPoster()).setJoinNotif(true);
			
			try {
				PostDatabase.getInstance().write();
				UserDatabase.getInstance().write();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			
		} else {
			// TODO
			// pop up that this ride already has too many riders
		}
	}
	
	public void joinDriver(String postID) {
		
		int temp = Integer.valueOf(postID);
		Rider r = ((Rider) PostDatabase.getInstance().searchDatabase(temp));

		Prospects driver = new Prospects();
		driver.setName(UserService.getInstance().getCurrentUser().getUsername());
		driver.setStatus(false);

		r.setDriver(driver);

		// System.out.println(PostDatabase.getInstance().quereyDatabase(r.getID()).toString);
		PostDatabase.getInstance().storeUpdate(r);

		UserDatabase.getInstance().queryDatabase(r.getPoster()).setJoinNotif(true);
		try {
			PostDatabase.getInstance().write();
			UserDatabase.getInstance().write();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	public void updateProspect(AbstractPost p, boolean [] b) {
		int counter = 0;
		
		if(p instanceof Rider) {
			
			if(!((Rider) p).getDriver().getStatus()) {
				((Rider) p).getDriver().setStatus(b[counter]);
			}
		}
		else if (p instanceof Driver) {
			
			for(Prospects i : ((Driver) p).getRiders()) {
				if(!i.getStatus()) {
					if(b[counter]) {
						i.setStatus(b[counter]);
						counter++;
					}
				}
			}
			
			ArrayList<Prospects> updated = new ArrayList<Prospects>();
			for(Prospects i : ((Driver) p).getRiders()) {
				if(!i.getStatus()) {
					updated.add(i);
				}
			}
			((Driver) p).setRiders(updated);
			
		}
	}
	
}
