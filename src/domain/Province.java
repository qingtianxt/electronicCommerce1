package domain;

import java.io.Serializable;

public class Province implements Serializable{
	private int id;
	private String name;
	private String postcode;
	private String create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Province() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", postcode=" + postcode + ", create_date=" + create_date
				+ "]";
	}
	
	
}
