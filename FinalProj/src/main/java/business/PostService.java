package business;

public class PostService {

	public boolean Validate(String[] input) {

		boolean driver = false;
		
		if (input.length == 0) {
			return false;
		}

		for (String i : input) {
			if (i.length() == 0) {
				return false;
			}
		}

		if (input.length > 8) {
			driver = true;
			
			if (Integer.valueOf(input[9]) < 1 || Integer.valueOf(input[9]) > 99) {
				return false;
			}
			
			
		} else {

		}

		// process date string?
		// or process date object if its been made by presentation layer

		// Store post if validation is successful
		StorePost(input, driver);
		return true;
	}

//	public boolean Validate(String[] input, int capacity) {
//		if(capacity < 1 || capacity > 99) {
//			return false;
//		}
//		
//		return Validate(input);
//	}

	public void StorePost(String[] input, boolean driver) {

		if (driver) {
			// create driver post
		} else {
			// create rider post
		}

		// should this create a post object to store in the database?
	}
}
