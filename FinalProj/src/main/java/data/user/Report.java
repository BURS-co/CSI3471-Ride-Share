package data.user;

public class Report {
	private User reportee;
	private User reporter;
	private String reason;

	/**
	 * @return the reportee
	 */
	public User getReportee() {
		return reportee;
	}

	/**
	 * @param reportee the reportee to set
	 */
	public void setReportee(User reportee) {
		this.reportee = reportee;
	}

	/**
	 * @return the reporter
	 */
	public User getReporter() {
		return reporter;
	}

	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
