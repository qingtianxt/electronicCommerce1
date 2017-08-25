package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import constant.constant;
import dao.orderDao;
import domain.order;
import utils.DataSourceUtils;

public class orderDaoImpl implements orderDao {
/**
 * ��Ӷ�����Ϣ
 */
	@Override
	public void add(order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql  = "insert into user_order(code,original_price,price,address_id,"
				+ "user_id,payment_type,status,create_date) values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql,order.getCode(),order.getOriginal_price(),
				order.getPrice(),order.getAddress_id(),order.getUser_id(),order.getPayment_type(),
				order.getStatus(),order.getCreate_date());
	}
/**
 * ���ݶ�����Ų�ѯ������Ϣ�����ڶ������ѯ���ڵĶ�����Ϣ�Ͳ�ѯ����������
 */
	@Override
	public order getByCode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_order where code=?";
		
		return qr.query(sql, new BeanHandler<>(order.class),code);
	}
	/**
	 * �����û�id��ѯ���ж���
	 */
	@Override
	public List<order> getByUser(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_order where user_id=?";
		
		return qr.query(sql, new BeanListHandler<>(order.class),id);
	}
	/**
	 * ���¶���״̬
	 */
	@Override
	public void updateStatus(int id, int status) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_order set status=? where id=?";
		qr.update(sql,status, id);
	}
	/**
	 * �����û�id��ҳ��ѯ���ж���
	 */
	@Override
	public List<order> getByPage(int id, int currPage) throws Exception {
		int pageSize =constant.ORDER_PAGESIZE;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_order where user_id=? limit ?,?";
		
		return qr.query(sql, new BeanListHandler<>(order.class), id,(currPage-1)*pageSize,pageSize);
	}
	/**
	 * �����ҵĶ�����������
	 */
	@Override
	public int count(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user_order where user_id=?";
		
		return ((Long)qr.query(sql, new ScalarHandler(), id)).intValue();
	}
	/**
	 * ���ݶ������ɾ������
	 */
	@Override
	public void delete(String code) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from user_order where code=?";
		qr.update(DataSourceUtils.getConnection(),sql, code);
	}
	
}
