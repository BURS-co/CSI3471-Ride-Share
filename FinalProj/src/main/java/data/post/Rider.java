package data.post;

import java.text.SimpleDateFormat;

public class Rider extends AbstractPost {
	
	private String driver;
	
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
		
		String result = "Rider" + "-" + poster + "-" + origin + "-" + dest + "-" + str;
		
		if(driver != null) {
			result += "-" + driver;
		}
		
		result += "\n";
		
		return result;
	}
	
	public void setDriver(String n) {
		this.driver = n;
	}
	
	public String getDriver() {
		return this.driver;
	}

}
