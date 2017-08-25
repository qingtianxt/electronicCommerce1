package domain;

/**
 * 商品订单Bean
 * 
 * @author svson
 *
 */
public class OrderProductBean {



	private int id;
	private int order_id;//订单id
	private order order;
	private int product_id;//商品id
	private product product;
	private float price;//价格
	private int number;//购买数量
	private String create_date;//创建时间

	public OrderProductBean() {
	}

	public OrderProductBean(product product, int number) {
		this.setProductBean(product);
		this.setNumber(number);
	}

	public OrderProductBean(order order, product product, int number) {
		this.setOrderBean(order);
		this.setProductBean(product);
		this.setNumber(number);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public order getOrderBean() {
		return order;
	}

	public void setOrderBean(order order) {
		this.order = order;
	}

	

	public product getProductBean() {
		return product;
	}

	public void setProductBean(product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderProductBean [id=" + id + ", order_id=" + order_id + ", product_id=" + product_id + ", price="
				+ price + ", number=" + number + ", create_date=" + create_date + "]";
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public order getOrder() {
		return order;
	}

	public void setOrder(order order) {
		this.order = order;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}


}