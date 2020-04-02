package data.survey;

public class Survey {
	private String name;
	private String[] responses;
	
	
	/**
	 * Initialize the survey
	 */
	public Survey(String name, String[] responses){
		this.name = name;
		this.responses = responses;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the responses
	 */
	public String[] getResponses() {
		return responses;
	}


	/**
	 * @param responses the responses to set
	 */
	public void setResponses(String[] responses) {
		this.responses = responses;
	}

}
