package service;

import domain.pageBean;
import domain.user;

public interface userService {

	void add(user u)throws Exception;

	user getByUserName(String username)throws Exception;

	user active(String code)throws Exception;

	pageBean<user> findAllByPage(int currPage)throws Exception;

	void update(user u)throws Exception;

	user getById(int id)throws Exception;


}
