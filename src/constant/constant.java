package constant;
public interface constant {
	/**
	 * 设置用户分页的每页的数量
	 */
	int PAGESIZE =1;
	/**
	 * 设置商品分页每页的数量
	 */
	int PRODUCT_PAGESIZE=2;
	/**
	 * 设置list用户的信息
	 */
	String USERBEANS ="userBeans";
	/**
	 * 设置单个用户的信息
	 */
	String USERBEAN ="userBean";
	/**
	 * 设置单个用户的信息
	 */
	String ADMINUSER ="adminUser";
	/**
	 * 设置用户分页每页的数量
	 */
	int USER_PAGESIZE = 2;
	/**
	 * 分页信息
	 */
	String PAGINGBEAN  ="pagingBean";
	
	/**
	 * 信息返回到jsp 
	 */
	String MSG="msg";
	/**
	 * 设置商品bean
	 */
	String PRODUCTBEANS = "productBeans";
	/**
	 * 设置单个商品bean
	 */
	String PRODUCTBEAN = "productBean";
	/**
	 * 设置session购物车
	 */
	String SESSION_CART = "cart";
	/**
	 * 设置收货地址信息
	 */
	String ADDRESSBEANS = "addressBeans";
	/**
	 * 设置收货地址的每页条数
	 */
	int  ADDRESS_PAGE= 2;
	/**
	 * 设置地址的分页pagingBean
	 */
	String ADDRESS_PAGINGBEAN = "pagingBean";
	/**
	 * 省份
	 */
	String PROVINCE="province";
	/**
	 * 城市
	 */
	String CITY = "city";
	/**
	 * 地区
	 */
	String REGION = "region";
	/**
	 * 设置订单beans
	 */
	String ORDERBEANS ="orderBeans";
	/**
	 * 设置单个订单bean
	 */
	String ORDERBEAN ="orderBean";
	/**
	 * 分页获取我的订单
	 */
	String ORDER_PAGINGBEAN = "order_pagingBean";
	/**
	 * 我的订单的每页显示的订单条数
	 */
	int ORDER_PAGESIZE = 2;
}
