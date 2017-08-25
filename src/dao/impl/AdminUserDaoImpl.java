package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import constant.constant;
import dao.AdminUserDao;
import domain.adminUser;
import utils.DataSourceUtils;

public class AdminUserDaoImpl implements AdminUserDao {
/**
 * �����û�����ȡ�û�
 */
	@Override
	public adminUser getUserByUserName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from admin where username=?";
		
		return qr.query(sql, new BeanHandler<>(adminUser.class),username);
	}
/**
 * ����û�
 */
	@Override
	public void add(adminUser au) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into admin(username,password,salt,create_date) values(?,?,?,?);";
		qr.update(sql,au.getUsername(),au.getPassword(),
				au.getSalt(),au.getCreate_date());
	}
	/**
	 * �޸��û�
	 */
	@Override
	public void update(adminUser au) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update admin set password=?,salt=? where id =?";
		qr.update(sql,au.getPassword(),au.getSalt(),au.getId());
	}
	/**
	 * ����������
	 */
	@Override
	public int totalCount() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from admin";
		return ((Long)(qr.query(sql,new ScalarHandler()))).intValue();
	}
	/**
	 * ���ݵ�ǰҳ��������
	 */
	@Override
	public List<adminUser> findByPage(int currPage) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="select * from admin limit ?,?";
		return qr.query(sql, new BeanListHandler<>(adminUser.class),(currPage-1)*constant.PAGESIZE,constant.PAGESIZE);
	}
	/**
	 * ͨ���û�id��ѯ�û������ڸ��²�����
	 */
	@Override
	public adminUser getById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="select * from admin where id=?";
		return qr.query(sql, new BeanHandler<>(adminUser.class),id);
	}
	/**
	 * ����idɾ���û���Ϣ
	 */
	@Override
	public void deleteById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="delete from admin where id=?";
		qr.update(sql,id);
	}
	

}
