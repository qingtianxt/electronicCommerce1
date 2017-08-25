package service.impl;

import java.util.List;

import constant.constant;
import dao.AdminUserDao;
import domain.adminUser;
import domain.pageBean;
import service.AdminUserService;
import utils.BeanFactory;

public class AdminUserServiceImpl implements AdminUserService {
/**
 * 检查是否存在这个用户
 */
	@Override
	public adminUser checkReg(String username) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		return ad.getUserByUserName(username);
	}
/**
 * 添加用户
 */
	@Override
	public void add(adminUser au) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		ad.add(au);
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public void update(adminUser au) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		adminUser au1 =ad.getUserByUserName(au.getUsername());
		au.setId(au1.getId());
		ad.update(au);
	}
	@Override
	public pageBean<adminUser> findAllByPage(int currPage) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		
		//获取总数量
		int count = ad.totalCount();
		//获取内容
		List<adminUser> list = ad.findByPage(currPage);
	
		pageBean<adminUser> bean = new pageBean<>(list, count, currPage, constant.PAGESIZE);
		
		return bean;
	}
	/**
	 * 通过id获取用户信息
	 */
	@Override
	public adminUser getById(int id) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		
		return ad.getById(id);
	}
	/**
	 * 根据id删除用户
	 */
	@Override
	public void deleteById(String id) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		ad.deleteById(id);
	}
	
}
