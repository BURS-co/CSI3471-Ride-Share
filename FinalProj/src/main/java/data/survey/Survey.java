package data.survey;

import java.util.Arrays;

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

	public Survey() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return null;
	}

}
