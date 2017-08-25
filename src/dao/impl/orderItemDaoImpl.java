package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.orderItemDao;
import domain.OrderProductBean;
import utils.DataSourceUtils;

public class orderItemDaoImpl implements orderItemDao{
/**
 * ��ӵ���������
 */
	@Override
	public void add(OrderProductBean bean) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql ="insert into user_order_product(order_id,product_id,price,number,create_date) values(?,?,?,?,?);";
		qr.update(DataSourceUtils.getConnection(),sql,bean.getOrderBean().getId(),
				bean.getProductBean().getId(),bean.getProductBean().getPrice(),bean.getNumber(),bean.getCreate_date());
	}
/**
 * �޸Ķ�������Ϣ
 */
	@Override
	public void update(OrderProductBean bean) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update user_order_product set order_id=?,product_id=?,price=?,"
				+ "number=? where id=?";
		qr.update(DataSourceUtils.getConnection(),sql,bean.getOrder_id(),bean.getProduct_id(),
				bean.getPrice(),bean.getNumber(),bean.getId());
	}
	/**
	 * ͨ������ʱ������ȡ�������Ҫ��Ϊ�˻�ȡ������id
	 */
	@Override
	public List<OrderProductBean> getByCreate_date(String create_date) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from user_order_product where create_date=?";
		return qr.query(DataSourceUtils.getConnection(),sql, new BeanListHandler<>(OrderProductBean.class), create_date);
	}
	@Override
	public List<OrderProductBean> getByOrderId(Integer id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_order_product where order_id=?";
		return qr.query(sql, new BeanListHandler<>(OrderProductBean.class), id);
	}
	/**
	 * ���ݶ���idɾ����������Ϣ
	 */
	@Override
	public void delete(Integer id) throws Exception {

		QueryRunner qr = new QueryRunner();
		String sql = "delete from user_order_product where order_id=?";
		qr.update(DataSourceUtils.getConnection(),sql, id);
	}

}
