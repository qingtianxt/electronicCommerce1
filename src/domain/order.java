package domain;

import java.util.List;

public class order {
	@Override
	public String toString() {
		return "order [id=" + id + ", code=" + code + ", original_price=" + original_price + ", price=" + price
				+ ", address_id=" + address_id + ", user_id=" + user_id + ", userBean=" + userBean + ", payment_type="
				+ payment_type + ", status=" + status + ", create_date=" + create_date + "]";
	}
	private Integer id;
	private String code;//�������
	private float original_price;//����ԭ�ۣ�������Ʒԭ����ӵļ۸�
	private float price;//��Ʒ�۸񣬣����У�
	private Integer address_id;//�ջ���ַ
	private orderAddressBean addressBean;
	private Integer user_id;//�û�id
	private user userBean;
	private Integer payment_type;//֧�����ͣ�0 ����֧����1���������
	private Integer status=0;//����״̬(δ֧������֧����δ�ջ������ջ�)
	private String create_date;//����ʱ��
	private List<OrderProductBean> orderProductBeans;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public orderAddressBean getAddressBean() {
		return addressBean;
	}
	public void setAddressBean(orderAddressBean addressBean) {
		this.addressBean = addressBean;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public user getUserBean() {
		return userBean;
	}
	public void setUserBean(user userBean) {
		this.userBean = userBean;
	}
	public Integer getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(Integer payment_type) {
		this.payment_type = payment_type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public List<OrderProductBean> getOrderProductBeans() {
		return orderProductBeans;
	}
	public void setOrderProductBeans(List<OrderProductBean> orderProductBeans) {
		this.orderProductBeans = orderProductBeans;
	}
	public order() {
		
	}
	

}
