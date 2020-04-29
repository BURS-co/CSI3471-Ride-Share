package data.user;

/**
 * @author Andrew Ammentorp, Joseph Yu, Joshua Huertas
 *	Class for a report
 */
public class Report {
	
	private User reportee;
	private User reporter;
	private String reason;

	/**
	 * Gets the reportee
	 * 
	 * @return the reportee
	 */
	public User getReportee() {
		return reportee;
	}

	/**
	 * Sets the reportee
	 * 
	 * @param reportee the reportee to set
	 */
	public void setReportee(User reportee) {
		this.reportee = reportee;
	}

	/**
	 * Gets the reporter
	 * 
	 * @return the reporter
	 */
	public User getReporter() {
		return reporter;
	}

	/**
	 * Sets the reporter
	 * 
	 * @param reporter the reporter to set
	 */
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	/**
	 * gets the reason of the report
	 * 
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Sets the reason of the report
	 * 
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
