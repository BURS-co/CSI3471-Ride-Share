package data.post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DriverPost extends Post {
	
	private Integer riderLimit; // max riders if driver post
	private ArrayList<String> riders = new ArrayList<String>();//if driver/rider post
	private String driver;
	
	public DriverPost() {
		super("driver");
		riders = null;
		riderLimit = 1;
		driver = null;
	}
	
	/**
	 * @return the riders
	 */
	public ArrayList<String> getRiders() {
		return riders;
	}

	/**
	 * @param riders the riders to set
	 */
	public void setRiders(ArrayList<String> riders) {
		this.riders = riders;
	}
	

	/**
	 * @param add user u to ride
	 */
	public void addRider(String u) {
		if (riders == null) {
			riders = new ArrayList<String>();
			riders.add(u);
		} else if (riders.size() < getRiderLimit()) {
			riders.add(u);
		}
		
	}

	/**
	 * @return the riderLimit
	 */
	public Integer getRiderLimit() {
		return riderLimit;
	}

	/**
	 * @param riderLimit the riderLimit to set
	 */
	public void setRiderLimit(int riderLimit) {
		this.riderLimit = riderLimit;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		String str = df.format(getDate()); 
		String s = getType() + "-" + getPoster() + "-" + getOrigin() + "-" + getDest() + "-" + str;
		s += "-" + driver + "-" + riderLimit + "-" + riders;
		if (riders != null) {
		  for (String p : riders) {
			  s = "-" + p;
		  }
		}
		s += "\n";
		return s;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
}

