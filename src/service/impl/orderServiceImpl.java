package service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import constant.constant;
import dao.orderAddressDao;
import dao.orderDao;
import dao.orderItemDao;
import dao.productDao;
import dao.userDao;
import domain.OrderProductBean;
import domain.order;
import domain.pageBean;
import domain.user;
import service.orderService;
import utils.BeanFactory;
import utils.DataSourceUtils;
/**
 * 订单模块
 * @author wzw
 *
 */
public class orderServiceImpl implements orderService {
	/**
	 * 添加订单
	 */
	@Override
	public void add(order order)throws Exception{
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		orderAddressDao ad =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderDao orderdao =(orderDao) BeanFactory.getBean("orderDao");
		
		
		//为订单添加收货地址信息
		order.setAddressBean(ad.getById(order.getAddress_id()));
		
		try {
			DataSourceUtils.startTransaction();
			//添加订单项
			List<OrderProductBean> op = order.getOrderProductBeans();
			for (OrderProductBean bean : op) {
				oi.add(bean);
			}
			orderdao.add(order);
			order order1=orderdao.getByCode(op.get(0).getOrderBean().getCode());
			//这个时候订单项还没有设置订单id，需要单独设置,订单项中没有订单项id，也需要获取
			List<OrderProductBean> list  = oi.getByCreate_date(order.getCreate_date());
					
					
			for (OrderProductBean bean : list) {
				bean.setOrder_id(order1.getId());
				oi.update(bean);
			}
			
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}
/**
 * 获取用户的所有订单 订单包含订单项，收货地址和订单信息
 */
	@Override
	public List<order> getAll(int id) throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		//获取订单信息
		 List<order> list =od.getByUser(id);
		for (order o : list) {
			//便利订单，获取地址和订单项信息
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//遍历订单项，获取订单项的商品信息
			for (OrderProductBean op : li) {
				op.setProduct(pd.getById(""+op.getProduct_id()));
			}
			o.setOrderProductBeans(li);
		}
		return list;
	}
	/**
	 * 
	 */
	@Override
	public void updateStatus(int id, int status) throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		od.updateStatus(id,status);
	}
	/**
	 * 分页获取订单信息
	 */
	@Override
	public pageBean<order> getByPage(int id, int currPage)throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		int totalCount =od.count(id);
		
		//获取订单信息
		 List<order> list =od.getByPage(id,currPage);
		for (order o : list) {
			//便利订单，获取地址和订单项信息
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//遍历订单项，获取订单项的商品信息
			for (OrderProductBean op : li) {
				op.setProduct(pd.getById(""+op.getProduct_id()));
			}
			o.setOrderProductBeans(li);
		}
		pageBean<order> page =new pageBean<>(list, totalCount, currPage, constant.ORDER_PAGESIZE);
		return page;
	}
	/**
	 * 后台 根据用户的手机（账号）获取订单
	 */
	@Override
	public List<order> getByAccount(String account) throws Exception {
		userDao ud =(userDao) BeanFactory.getBean("userDao");
		
		user u = ud.getByUserName(account);
		if(u==null){
			return null;
		}
		else{
			orderDao od =(orderDao) BeanFactory.getBean("orderDao");
			orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
			orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
			productDao pd =(productDao) BeanFactory.getBean("productDao");
			//获取订单信息
			 List<order> list =od.getByUser(u.getId());
			for (order o : list) {
				//便利订单，获取地址和订单项信息
				o.setAddressBean(oa.getById(o.getAddress_id()));
				
				List<OrderProductBean> li = oi.getByOrderId(o.getId());
				//遍历订单项，获取订单项的商品信息
				for (OrderProductBean op : li) {
					op.setProduct(pd.getById(""+op.getProduct_id()));
				}
				o.setOrderProductBeans(li);
			}
			return list;
		}
	}
	/**
	 * 根据订单
	 */
	@Override
	public order getByCode(String code) throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		//获取订单信息
		 order o =od.getByCode(code);
			//便利订单，获取地址和订单项信息
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//遍历订单项，获取订单项的商品信息
			for (OrderProductBean op : li) {
				op.setProduct(pd.getById(""+op.getProduct_id()));
			}
			o.setOrderProductBeans(li);
		return o;
	}
	/**
	 * 删除单个订单，删除他的订单项
	 * @throws Exception 
	 */
	@Override
	public void delete(String code) throws Exception{
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		
		
		try {
			order order = od.getByCode(code);
			DataSourceUtils.startTransaction();
			oi.delete(order.getId());
			
			od.delete(code);
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}
}
