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
 * ����ģ��
 * @author wzw
 *
 */
public class orderServiceImpl implements orderService {
	/**
	 * ��Ӷ���
	 */
	@Override
	public void add(order order)throws Exception{
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		orderAddressDao ad =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderDao orderdao =(orderDao) BeanFactory.getBean("orderDao");
		
		
		//Ϊ��������ջ���ַ��Ϣ
		order.setAddressBean(ad.getById(order.getAddress_id()));
		
		try {
			DataSourceUtils.startTransaction();
			//��Ӷ�����
			List<OrderProductBean> op = order.getOrderProductBeans();
			for (OrderProductBean bean : op) {
				oi.add(bean);
			}
			orderdao.add(order);
			order order1=orderdao.getByCode(op.get(0).getOrderBean().getCode());
			//���ʱ�򶩵��û�����ö���id����Ҫ��������,��������û�ж�����id��Ҳ��Ҫ��ȡ
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
 * ��ȡ�û������ж��� ��������������ջ���ַ�Ͷ�����Ϣ
 */
	@Override
	public List<order> getAll(int id) throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		//��ȡ������Ϣ
		 List<order> list =od.getByUser(id);
		for (order o : list) {
			//������������ȡ��ַ�Ͷ�������Ϣ
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//�����������ȡ���������Ʒ��Ϣ
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
	 * ��ҳ��ȡ������Ϣ
	 */
	@Override
	public pageBean<order> getByPage(int id, int currPage)throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		int totalCount =od.count(id);
		
		//��ȡ������Ϣ
		 List<order> list =od.getByPage(id,currPage);
		for (order o : list) {
			//������������ȡ��ַ�Ͷ�������Ϣ
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//�����������ȡ���������Ʒ��Ϣ
			for (OrderProductBean op : li) {
				op.setProduct(pd.getById(""+op.getProduct_id()));
			}
			o.setOrderProductBeans(li);
		}
		pageBean<order> page =new pageBean<>(list, totalCount, currPage, constant.ORDER_PAGESIZE);
		return page;
	}
	/**
	 * ��̨ �����û����ֻ����˺ţ���ȡ����
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
			//��ȡ������Ϣ
			 List<order> list =od.getByUser(u.getId());
			for (order o : list) {
				//������������ȡ��ַ�Ͷ�������Ϣ
				o.setAddressBean(oa.getById(o.getAddress_id()));
				
				List<OrderProductBean> li = oi.getByOrderId(o.getId());
				//�����������ȡ���������Ʒ��Ϣ
				for (OrderProductBean op : li) {
					op.setProduct(pd.getById(""+op.getProduct_id()));
				}
				o.setOrderProductBeans(li);
			}
			return list;
		}
	}
	/**
	 * ���ݶ���
	 */
	@Override
	public order getByCode(String code) throws Exception {
		orderDao od =(orderDao) BeanFactory.getBean("orderDao");
		orderAddressDao oa =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		orderItemDao oi =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		//��ȡ������Ϣ
		 order o =od.getByCode(code);
			//������������ȡ��ַ�Ͷ�������Ϣ
			o.setAddressBean(oa.getById(o.getAddress_id()));
			
			List<OrderProductBean> li = oi.getByOrderId(o.getId());
			//�����������ȡ���������Ʒ��Ϣ
			for (OrderProductBean op : li) {
				op.setProduct(pd.getById(""+op.getProduct_id()));
			}
			o.setOrderProductBeans(li);
		return o;
	}
	/**
	 * ɾ������������ɾ�����Ķ�����
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
