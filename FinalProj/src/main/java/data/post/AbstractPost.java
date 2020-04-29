package data.post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.UserService;

public abstract class AbstractPost {
	protected String poster;
	protected String origin;
	protected String dest;
	protected Date date;

	public AbstractPost(String[] input) {

		SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm a");

		this.poster = UserService.getInstance().getCurrentUser().getUsername();
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

	public AbstractPost() {
		// TODO Auto-generated constructor stub
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
}
