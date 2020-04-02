package survey;

public class Survey {
	private String name;
	private static String[] questions;
	private String[] responses;
	
	
	/**
	 * Initialize the survey
	 */
	public Survey(){
		name = null;
		questions = new String[5];
		questions[1] = "How would you rate your ride?";
		questions[2] = "Were you able to find a ride with ease?";
		responses = new String[5];
		for (int i = 0; i < 5; i++) {
			responses[i] = null;
		}
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
