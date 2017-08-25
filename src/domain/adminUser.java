package domain;

import java.io.Serializable;
import java.util.Date;

public class adminUser implements Serializable{
	private String id;
	private String username;
	private String password;
	private String salt;
	private String create_date;
	
	public String getUsername() {
		return username;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Override
	public String toString() {
		return "adminUser [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", create_date=" + create_date + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public adminUser() {
		
	}
}
