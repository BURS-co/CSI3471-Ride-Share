package data.user;

/**
 * @author Andrew Ammentorp, Joseph Yu, Joshua Huertas Class for a report
 */
public class Report {

	private String reportee;
	private String reporter;
	private String reason;

	/**
	 * Gets the reportee
	 * 
	 * @return the reportee
	 */
	public String getReportee() {
		return reportee;
	}

	/**
	 * Sets the reportee
	 * 
	 * @param reportee the reportee to set
	 */
	public void setReportee(String reportee) {
		this.reportee = reportee;
	}

	/**
	 * Gets the reporter
	 * 
	 * @return the reporter
	 */
	public String getReporter() {
		return reporter;
	}

	/**
	 * Sets the reporter
	 * 
	 * @param reporter the reporter to set
	 */
	public void setReporter(String reporter) {
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

	@Override
	public String toString() {
		return reportee + ",,," + reporter + ",,," + reason + "\n";
	}

}
