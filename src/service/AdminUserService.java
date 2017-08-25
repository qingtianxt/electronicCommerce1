package service;

import java.util.List;

import domain.adminUser;
import domain.pageBean;

public interface AdminUserService {

	adminUser checkReg(String username)throws Exception;

	void add(adminUser au)throws Exception;

	void update(adminUser au)throws Exception;

	pageBean<adminUser> findAllByPage(int currPage)throws Exception;

	adminUser getById(int id)throws Exception;

	void deleteById(String id)throws Exception;

	
}
