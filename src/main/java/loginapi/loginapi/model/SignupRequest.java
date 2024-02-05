package loginapi.loginapi.model;

public class SignupRequest {

	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", password=" + password + "]";
	}
	public SignupRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
