package domain;

import java.io.Serializable;
import java.util.List;

public class product implements Serializable{
	private Integer id;
	private String name;
	private float original_price;
	
	@Override
	public String toString() {
		return "product [id=" + id + ", name=" + name + ", original_price=" + original_price + ", price=" + price
				+ ", intro=" + intro + ", product_type_id=" + product_type_id + ", number=" + number + ", sell_number="
				+ sell_number + ", pic=" + pic + ", product_properties=" + product_properties + ", create_date="
				+ create_date + ", productOptionBeans=" + productOptionBeans + "]";
	}
	private float price;
	private String intro;
	private Integer product_type_id;
	
	private Integer number;
	private Integer sell_number;
	private String pic;
	
	private String product_properties;
	private String create_date;
	
	private List<productOption> productOptionBeans;
	
	public List<productOption> getProductOptionBeans() {
		return productOptionBeans;
	}
	public void setProductOptionBeans(List<productOption> productOptionBeans) {
		this.productOptionBeans = productOptionBeans;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getProduct_type_id() {
		return product_type_id;
	}
	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSell_number() {
		return sell_number;
	}
	public void setSell_number(Integer sell_number) {
		this.sell_number = sell_number;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProduct_properties() {
		return product_properties;
	}
	public void setProduct_properties(String product_properties) {
		this.product_properties = product_properties;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
}
