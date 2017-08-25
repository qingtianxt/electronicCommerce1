package service.impl;

import java.util.List;

import constant.constant;
import dao.userDao;
import domain.pageBean;
import domain.user;
import service.userService;
import utils.BeanFactory;
import utils.MailUtils;

public class userServiceImpl implements userService {
/**
 * 添加前台用户信息
 */
	@Override
	public void add(user u) throws Exception {
		userDao ud = (userDao) BeanFactory.getBean("userDao");
		ud.add(u);
		
		//发送邮件
		String msg = "欢迎您注册成我们的一员,<a href='http://localhost:8080/electronicCommerce1/user?method=active&code="+u.getCode()+"'>点此激活</a>";
		MailUtils.sendMail(u.getEmail(), msg);
	}
/**
 * 根据用户名获取用户信息
 */
	@Override
	public user getByUserName(String username) throws Exception {
		userDao ud = (userDao) BeanFactory.getBean("userDao");
		
		return ud.getByUserName(username);
	}
	/**
	 * 用户激活
	 */
	@Override
	public user active(String code) throws Exception {
		userDao ud= (userDao) BeanFactory.getBean("userDao");
		user u =ud.getByCode(code);
		if(u==null){
			return null;
		}
		u.setStatus(1);
		ud.update(u);
		return u;
	}
	@Override
	public pageBean<user> findAllByPage(int currPage) throws Exception {
		userDao ud = (userDao) BeanFactory.getBean("userDao");
		int totalcount = ud.count();
		int pageSize =constant.USER_PAGESIZE;
		List<user> list = ud.findAllByPage(currPage,pageSize);
		pageBean<user> pb = new pageBean<>(list, totalcount, currPage, pageSize);
		
		return pb;
	}
	@Override
	public void update(user u) throws Exception {
		userDao ud = (userDao) BeanFactory.getBean("userDao");
		ud.update(u);
	}
	@Override
	public user getById(int id) throws Exception {
		userDao ud = (userDao) BeanFactory.getBean("userDao");
		return ud.getById(id);
	}
	
}
