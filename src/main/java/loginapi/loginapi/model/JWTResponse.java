package loginapi.loginapi.model;

public class JWTResponse {
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTResponse [token=" + token + "]";
	}

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public JWTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
