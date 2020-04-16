package business;

public class PostService {

	public boolean Validate(String[] input) {
		
		if(input.length == 0) {
			return false;
		}
		
		for(String i : input) {
			if(i.length() == 0) {
				return false;
			}
		}
		
		
		
		//process date string?
		//or process date object if its been made by presentation layer
		
		return true;
	}

	public boolean Validate(String[] input, int capacity) {
		if(capacity < 1 || capacity > 99) {
			return false;
		}
		
		return Validate(input);
	}
}
