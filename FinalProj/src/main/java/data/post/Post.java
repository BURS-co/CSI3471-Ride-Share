package data.post;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	
	private String type;	//either driver or rider
	private String poster;	//username of user
	private String origin;
	private String dest;
	private Date date;
	
	public Post() {
		type = "rider";
		poster = null;
		origin = null;
		dest = null;
		date = null;
	}
	
	public Post(String type) {
		this.type = type;
		poster = null;
		origin = null;
		dest = null;
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
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * @return the dest
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * @param dest the dest to set
	 */
	public void setDest(String dest) {
		this.dest = dest;
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
		return type + "-" + poster + "-" + origin + "-" + dest + "-" + str + "\n";
	}

}