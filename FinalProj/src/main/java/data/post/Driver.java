package data.post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Driver extends AbstractPost{

	private Integer riderLimit; // max riders if driver post
	private ArrayList<String> riders = new ArrayList<String>();//if driver/rider post
	
	public Driver(String[] list) {
		super(list);
		this.riderLimit = Integer.valueOf(list[list.length-1]);
	}
	
	boolean addProspects() {
		boolean result = true;
		
		//TODO adding a prospect to the riders list
		
		return result;
	}
	
	public Integer getRiderLimit() {
		return riderLimit;
	}

	public void setRiderLimit(Integer riderLimit) {
		this.riderLimit = riderLimit;
	}

	public ArrayList<String> getRiders() {
		return riders;
	}

	public void setRiders(ArrayList<String> riders) {
		this.riders = riders;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		String str = df.format(getDate()); 
		String s = "Driver" + "-" + getPoster() + "-" + getOrigin() + "-" + getDest() + "-" + str;
		s += "-" + getPoster() + "-" + riderLimit + "-" + riders;
		if (riders != null) {
		  for (String p : riders) {
			  s = "-" + p;
		  }
		}
		s += "\n";
		return s;
	}

}
