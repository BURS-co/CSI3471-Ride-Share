package data.user;

import java.util.List;

import data.post.AbstractPost;

/**
 * @author Andrew Ammentorp Class responsible for the Users of the application
 */
public class User {
	protected String username;
	protected String password;
	protected String email;
	protected String phoneNumber;
	protected String gradMonth;
	protected String gradYear;
	
	protected List<AbstractPost> posts;
	protected boolean joinNotif;
	protected boolean postCanceledNotif;

	/**
	 * Constructor for user
	 */
	public User() {
		username = null;
		password = null;
		email = null;
		phoneNumber = null;
		gradMonth = null;
		gradYear = null;
	}

	@Override
	public String toString() {
		return username + ",,," + email + ",,," + phoneNumber + ",,," + gradMonth + ",,," + gradYear + ",,," + password
				+ ",,,false\n";
	}

	/**
	 * Gets the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number
	 * 
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number
	 * 
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets user's graduation month
	 * 
	 * @return the gradMonth
	 */
	public String getGradMonth() {
		return gradMonth;
	}

	/**
	 * Set user's graduation month
	 * 
	 * @param gradYear the gradYear to set
	 */
	public void setGradMonth(String gradMonth) {
		this.gradMonth = gradMonth;
	}

	/**
	 * Gets user's graduation year
	 * 
	 * @return gradYear the gradYear
	 */
	public String getGradYear() {
		return gradYear;
	}

	/**
	 * Sets user's graduation year
	 * 
	 * @param gradYear the gradYear to set
	 */
	public void setGradYear(String gradYear) {
		this.gradYear = gradYear;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gradMonth == null) ? 0 : gradMonth.hashCode());
		result = prime * result + ((gradYear == null) ? 0 : gradYear.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	// For remove
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gradMonth == null) {
			if (other.gradMonth != null)
				return false;
		} else if (!gradMonth.equals(other.gradMonth))
			return false;
		if (gradYear == null) {
			if (other.gradYear != null)
				return false;
		} else if (!gradYear.equals(other.gradYear))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void setPosts(List<AbstractPost> l) {
		this.posts = l;
	}
	
	public List<AbstractPost> getPosts() {
		return this.posts;
	}
	
	public boolean getJoinNotif() {
		return this.joinNotif;
	}
	
	public void setJoinNotif(boolean t) {
		this.joinNotif = t;
	}
	
	public void setPostCanceledNotif(boolean t) {
		this.postCanceledNotif = t;
	}
	
	public boolean getPostCanceledNotif() {
		return this.postCanceledNotif;
	}
	
}
