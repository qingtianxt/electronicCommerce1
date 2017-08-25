package dao;

import java.util.List;

import domain.order;

public interface orderDao {

	void add(order order)throws Exception;
	
	order getByCode(String code)throws Exception;

	List<order> getByUser(int id)throws Exception;

	void updateStatus(int id, int status)throws Exception;

	List<order> getByPage(int id, int currPage)throws Exception;

	int count(int id)throws Exception;

	void delete(String code)throws Exception;
	

}
