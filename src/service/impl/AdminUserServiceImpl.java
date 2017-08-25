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
 * ����Ƿ��������û�
 */
	@Override
	public adminUser checkReg(String username) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		return ad.getUserByUserName(username);
	}
/**
 * ����û�
 */
	@Override
	public void add(adminUser au) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		ad.add(au);
	}
	/**
	 * �޸��û���Ϣ
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
		
		//��ȡ������
		int count = ad.totalCount();
		//��ȡ����
		List<adminUser> list = ad.findByPage(currPage);
	
		pageBean<adminUser> bean = new pageBean<>(list, count, currPage, constant.PAGESIZE);
		
		return bean;
	}
	/**
	 * ͨ��id��ȡ�û���Ϣ
	 */
	@Override
	public adminUser getById(int id) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		
		return ad.getById(id);
	}
	/**
	 * ����idɾ���û�
	 */
	@Override
	public void deleteById(String id) throws Exception {
		AdminUserDao ad = (AdminUserDao) BeanFactory.getBean("AdminUserDao");
		ad.deleteById(id);
	}
	
}
