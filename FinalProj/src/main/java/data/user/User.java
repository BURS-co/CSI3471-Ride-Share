package data.user;

/**
 * @author Andrew Ammentorp
 *	Class responsible for the Users of the application
 */
public class User {
	protected String username;
	protected String password;
	protected String email;
	protected String phoneNumber;
	protected String gradMonth;
	protected String gradYear;
	protected boolean isAdmin;

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
		return username + " " + email + " " + phoneNumber + " " + gradMonth + " " + gradYear + " " + password + " false\n";
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number
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

	/**
	 * If the user is an admin or not
	 * 
	 * @return if admin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * Sets if user is an admin
	 * 
	 * @param admin the isAdmin boolean to set
	 */
	public void setIsAdmin(String admin) {
		if (admin.equals("true"))
			this.isAdmin = true;
		else
			this.isAdmin = false;
	}
}
