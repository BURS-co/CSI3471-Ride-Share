package data.post;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	
	private String type;	//either driver or rider
	private String poster;	//username of user
	private String airport;
	private Date date;
	
	public Post() {
		type = "rider";
		poster = null;
		airport = null;
		date = null;
	}
	
	public Post(String type) {
		this.type = type;
		poster = null;
		airport = null;
		date = null;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * @param poster the poster to set
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}

	/**
	 * @return the airport
	 */
	public String getAirport() {
		return airport;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(String airport) {
		this.airport = airport;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		//SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		String str = df.format(date); 
		return type + "-" + poster + "-" + airport + "-" + str + "\n";
	}

}