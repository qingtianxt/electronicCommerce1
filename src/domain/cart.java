package domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class cart {
	private Map<Integer, cartItem> map = new LinkedHashMap<>();
	
	/**
	 * ��ȡ���еĹ��ﳵ��
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
	 * �����Ʒ�����ﳵ
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
	 * �ӹ��ﳵ��ɾ��ָ���Ĺ��ﳵ��
	 * @param id
	 */
	public void removeFromCart(Integer id){
		cartItem cartItem = map.remove(id);
	}
	/**
	 * ��չ��ﳵ
	 */
	public void clearCart(){
		map.clear();
	}
}
