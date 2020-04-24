package data.survey;

/**
 * @author Andrew Ammentorp, Joseph Perez
 *	Class responsible for a user survey
 */
public class Survey {
	private String name;
	private String[] responses;

	/**
	 * Initialize the survey
	 */
	public Survey(String name, String[] responses) {
		this.name = name;
		this.responses = responses;
	}

	/**
	 * gets the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name to the name of user answering survey
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets user's repsonses
	 * 
	 * @return the responses
	 */
	public String[] getResponses() {
		return responses;
	}

	/**
	 * Sets user's responses
	 * @param responses the responses to set
	 */
	public void setResponses(String[] responses) {
		this.responses = responses;
	}

}
