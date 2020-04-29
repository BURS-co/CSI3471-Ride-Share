package data.post;

public class Prospects {
	private String name;
	private boolean status;

	public Prospects(String name) {
		this.name = name;
	}

	public Prospects() {
		// TODO Auto-generated constructor stub
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String string) {
		this.status = string.equalsIgnoreCase("true");

	}
}
