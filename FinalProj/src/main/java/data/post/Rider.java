package data.post;

import java.text.SimpleDateFormat;

public class Rider extends AbstractPost {
	public Rider(String[] list) {
		super(list);
	}

	public Rider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
		String str = df.format(date);
		return "Rider" + "-" + poster + "-" + origin + "-" + dest + "-" + str + "\n";
	}

}
