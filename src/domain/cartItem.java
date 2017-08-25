package domain;

import java.io.Serializable;

public class cartItem implements Serializable{
	private product product;
	private int num;
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public cartItem(product product, int num) {
		this.product = product;
		this.num = num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public cartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
