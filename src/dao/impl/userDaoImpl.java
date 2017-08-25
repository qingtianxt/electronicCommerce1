package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.userDao;
import domain.user;
import utils.DataSourceUtils;

public class userDaoImpl implements userDao {

	@Override
	public void add(user u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user(account,password,salt,"
				+ "nickname,truename,sex,"
				+ "pic,status,create_date,email,code) values(?,?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, u.getAccount(),u.getPassword(),u.getSalt(),
				u.getNickname(),u.getTruename(),u.getSex(),
				u.getPic(),u.getStatus(),u.getCreate_date(),u.getEmail(),u.getCode());
	}
/**
 * 根据用户名获取用户信息
 * @throws Exception 
 */
	@Override
	public user getByUserName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where account=?";
		
		return qr.query(sql, new BeanHandler<>(user.class),username);
	}
	/**
	 * 根据激活码查找用户信息
	 */
	@Override
	public user getByCode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where code=?";
		
		return qr.query(sql, new BeanHandler<>(user.class),code);
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public void update(user u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set account=?,password=?,salt=?,"
				+ "nickname=?,truename=?,sex=?,"
				+ "pic=?,status=?,email=?,code=? where id=?";
		qr.update(sql,u.getAccount(),u.getPassword(),u.getSalt(),
				u.getNickname(),u.getTruename(),u.getSex(),
				u.getPic(),u.getStatus(),u.getEmail(),null,u.getId());
	}
	/**
	 * 计算用户的总数量
	 */
	@Override
	public int count() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user";
		return ((Long)qr.query(sql, new ScalarHandler())).intValue();
	}
	/**
	 * 分页查询用户信息
	 */
	@Override
	public List<user> findAllByPage(int currPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select  *from user limit ?,?";
		return qr.query(sql, new BeanListHandler<>(user.class),(currPage-1)*pageSize,pageSize);
	}
	
	@Override
	public user getById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where id =?";
		
		return qr.query(sql, new BeanHandler<>(user.class),id);
	}

}
