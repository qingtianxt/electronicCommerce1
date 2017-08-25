package domain;

import java.io.Serializable;

public class user implements Serializable{
	private int id;
	private String account;
	private String password;
	
	@Override
	public String toString() {
		return "user [id=" + id + ", account=" + account + ", password=" + password + ", salt=" + salt + ", status="
				+ status + ", create_date=" + create_date + ", sex=" + sex + ", nickname=" + nickname + ", truename="
				+ truename + ", pic=" + pic + ", email=" + email + ", code=" + code + "]";
	}
	private String salt;
	private int status;//0代表未激活，1代表已激活;
	private String create_date;
	
	private int sex;//0代表男性，
	private String nickname;
	private String truename;
	
	private String pic;//头像
	private String email;
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
