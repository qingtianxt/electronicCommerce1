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
 * 根据用户名获取用户
 */
	@Override
	public adminUser getUserByUserName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from admin where username=?";
		
		return qr.query(sql, new BeanHandler<>(adminUser.class),username);
	}
/**
 * 添加用户
 */
	@Override
	public void add(adminUser au) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into admin(username,password,salt,create_date) values(?,?,?,?);";
		qr.update(sql,au.getUsername(),au.getPassword(),
				au.getSalt(),au.getCreate_date());
	}
	/**
	 * 修改用户
	 */
	@Override
	public void update(adminUser au) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update admin set password=?,salt=? where id =?";
		qr.update(sql,au.getPassword(),au.getSalt(),au.getId());
	}
	/**
	 * 计算总数量
	 */
	@Override
	public int totalCount() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from admin";
		return ((Long)(qr.query(sql,new ScalarHandler()))).intValue();
	}
	/**
	 * 根据当前页返回数据
	 */
	@Override
	public List<adminUser> findByPage(int currPage) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="select * from admin limit ?,?";
		return qr.query(sql, new BeanListHandler<>(adminUser.class),(currPage-1)*constant.PAGESIZE,constant.PAGESIZE);
	}
	/**
	 * 通过用户id查询用户（用于更新操作）
	 */
	@Override
	public adminUser getById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="select * from admin where id=?";
		return qr.query(sql, new BeanHandler<>(adminUser.class),id);
	}
	/**
	 * 根据id删除用户信息
	 */
	@Override
	public void deleteById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="delete from admin where id=?";
		qr.update(sql,id);
	}
	

}
