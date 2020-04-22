package data.user;

/**
 * @author Andrew
 *
 */
public class User {
	protected String username;
	protected String password;
	protected String email;
	protected String phoneNumber;
	protected String gradMonth;
	protected String gradYear;
	protected boolean isAdmin;

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the gradMonth
	 */
	public String getGradMonth() {
		return gradMonth;
	}

	/**
	 * @param gradYear the gradYear to set
	 */
	public void setGradMonth(String gradMonth) {
		this.gradMonth = gradMonth;
	}

	/**
	 * @return the gradYear
	 */
	public String getGradYear() {
		return gradYear;
	}

	/**
	 * @param gradYear the gradYear to set
	 */
	public void setGradYear(String gradYear) {
		this.gradYear = gradYear;
	}

	/**
	 * @return if admin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param admin the isAdmin boolean to set
	 */
	public void setIsAdmin(String admin) {
		if (admin.equals("true"))
			this.isAdmin = true;
		else
			this.isAdmin = false;
	}
}
