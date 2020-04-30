/**
 * @author Joseph Yu, Joshua Huertas
 */
package data.post;

import java.text.SimpleDateFormat;

public class Rider extends AbstractPost {

	private Prospects driver;

	public Rider(String[] list) {
		super(list);
	}

	public Rider(String id) {
		// TODO Auto-generated constructor stub
		this.ID = Integer.parseInt(id);
		Rider.ID_counter = this.ID+1;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
		String str = df.format(date);

		String result = "Rider" + "-" + this.ID + "-" + poster + "-" + origin + "-" + dest + "-" + str;

		if (driver != null) {
			result += "-" + driver.getName() + "-" + driver.getStatus();
		}
		result += "\n";

		return result;
	}

	public void setDriver(Prospects n) {
		this.driver = n;
	}

	public Prospects getDriver() {
		return this.driver;
	}

}
