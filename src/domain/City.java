package domain;

import java.io.Serializable;

public class City implements Serializable{
	private int id;
	private String name;
	private String postcode;
	private int province_id;
	private String create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public City() {
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", postcode=" + postcode + ", province_id=" + province_id
				+ ", create_date=" + create_date + "]";
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
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
}
