package dao;

import java.util.List;

import domain.user;

public interface userDao {

	void add(user u)throws Exception;

	user getByUserName(String username)throws Exception;

	user getByCode(String code)throws Exception;

	void update(user u)throws Exception;

	int count()throws Exception;

	List<user> findAllByPage(int currPage, int pageSize)throws Exception;

	user getById(int id)throws Exception;

}
