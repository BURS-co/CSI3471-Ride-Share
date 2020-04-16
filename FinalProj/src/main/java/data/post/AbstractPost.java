package data.post;

import java.util.Date;

public abstract class AbstractPost {
    protected String poster;
    protected String origin;
    protected String dest;
    protected Date date;
    
    public AbstractPost(String [] list) {
    	//TODO occupy the fields
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
