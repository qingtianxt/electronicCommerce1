package dao;

import java.util.List;

import domain.adminUser;

public interface AdminUserDao {

	adminUser getUserByUserName(String username) throws Exception;

	void add(adminUser au) throws Exception;

	void update(adminUser au) throws Exception;

	int totalCount()throws Exception;

	List<adminUser> findByPage(int currPage)throws Exception;

	adminUser getById(int id)throws Exception;

	void deleteById(String id)throws Exception;

	
}
