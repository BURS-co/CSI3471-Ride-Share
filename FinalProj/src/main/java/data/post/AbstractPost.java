/**
 * @author Joseph Yu, Joshua Huertas
 */
package data.post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.UserService;

public abstract class AbstractPost {
	protected static int ID_counter = 0;
	protected int ID;
	protected String poster;
	protected String origin;
	protected String dest;
	protected Date date;

	public AbstractPost(String[] input) {
		this.ID = ID_counter;
		ID_counter++;

		SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a");

		this.poster = UserService.getInstance().getCurrentUser().getEmail();
		// this.poster = Application.loggedIn.getUsername();
		this.origin = input[0];
		this.dest = input[1];

		try {
			this.date = f
					.parse(input[3] + " " + input[2] + " " + input[4] + " " + input[5] + ":" + input[6] + " " + input[7]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public AbstractPost() {
		// TODO Auto-generated constructor stub
		// this.ID = ID_counter;
		// ID_counter++;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public abstract String toString();

	public boolean isExpired() {
		Date now = new Date();
		return now.after(getDate());
	}
}
