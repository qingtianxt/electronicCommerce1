package domain;

import java.io.Serializable;

public class Region implements Serializable{
	private int id;
	private String name;
	private String postcode;
	private int city_id;
	private String create_date;
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
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Region() {
		super();
	}
	@Override
	public String toString() {
		return "Region [id=" + id + ", name=" + name + ", postcode=" + postcode + ", city_id=" + city_id
				+ ", create_date=" + create_date + "]";
	}
	
}
