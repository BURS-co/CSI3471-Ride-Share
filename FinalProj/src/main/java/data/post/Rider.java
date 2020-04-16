package data.post;

import java.text.SimpleDateFormat;

public class Rider extends AbstractPost{
	
	public Rider(String[] list) {
		super(list);
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
		String str = df.format(date); 
		return "Rider" + "-" + poster + "-" + origin + "-" + dest + "-" + str + "\n";
	}

}
