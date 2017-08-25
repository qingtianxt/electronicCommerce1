package domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class cart {
	private Map<Integer, cartItem> map = new LinkedHashMap<>();
	
	/**
	 * 获取所有的购物车项
	 * @return
	 */
	public Collection<cartItem> getItems(){
		return map.values();
	}
	public Map<Integer, cartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, cartItem> map) {
		this.map = map;
	}
	/**
	 * 添加商品到购物车
	 * @param item
	 */
	public void add2Cart(cartItem item){
		Integer id = item.getProduct().getId();
		if(map.containsKey(id)){
			cartItem oItem = map.get(id);
			oItem.setNum(oItem.getNum()+item.getNum());
		}else{
			map.put(id, item);
		}
	}
	/**
	 * 从购物车中删除指定的购物车项
	 * @param id
	 */
	public void removeFromCart(Integer id){
		cartItem cartItem = map.remove(id);
	}
	/**
	 * 清空购物车
	 */
	public void clearCart(){
		map.clear();
	}
}
