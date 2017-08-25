package domain;

import java.io.Serializable;

public class productOption implements Serializable{
	private int id;
	private String name;
	private int product_type_property_id;
	private String sort;
	private String create_date;
	
	private productProperty productPropertyBean;
	
	
	
	public productProperty getProductPropertyBean() {
		return productPropertyBean;
	}
	public void setProductPropertyBean(productProperty productPropertyBean) {
		this.productPropertyBean = productPropertyBean;
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
	public int getProduct_type_property_id() {
		return product_type_property_id;
	}
	public void setProduct_type_property_id(int product_type_property_id) {
		this.product_type_property_id = product_type_property_id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
}
