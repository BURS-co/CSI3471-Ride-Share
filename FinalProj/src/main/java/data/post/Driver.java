package data.post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Driver extends AbstractPost{
	private Integer riderLimit; // max riders if driver post
	private ArrayList<Prospects> riders = new ArrayList<Prospects>();//if driver/rider post
	
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

	public ArrayList<Prospects> getRiders() {
		return riders;
	}

	public void setRiders(ArrayList<Prospects> riders) {
		this.riders = riders;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		String str = df.format(getDate()); 
		String s = "Driver" + "-" + getPoster() + "-" + getOrigin() + "-" + getDest() + "-" + str;
		s += "-" + getPoster() + "-" + riderLimit + "-" + riders;
		if (riders != null) {
		  for (Prospects p : riders) {
			  s = "-" + p.getName();
		  }
		}
		s += "\n";
		return s;
	}

}
