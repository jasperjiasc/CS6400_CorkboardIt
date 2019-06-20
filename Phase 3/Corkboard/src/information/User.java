package information;

public class User {
	private String userEmail;
	private String first_name;
	private String last_name;

	public User(String userEmail, String first_name, String last_name) {
		super();
		this.userEmail = userEmail;
		this.first_name = first_name;
		this.last_name = last_name;
	}

//	public User(String email) {
//		setUserEmail(email);
//	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
}
