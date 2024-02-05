package loginapi.loginapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LoginUser")
public class LoginUser {
	
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUser(int id, String name, String username, String password, String role, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", enabled=" + enabled + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	
	private int id;
	private String name;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String role;
	private boolean enabled;
}
