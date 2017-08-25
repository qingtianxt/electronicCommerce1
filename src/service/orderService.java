package service;

import java.util.List;

import domain.order;
import domain.pageBean;

public interface orderService {

	void add(order order) throws Exception;

	List<order> getAll(int id)throws Exception;

	void updateStatus(int id, int status)throws Exception;

	pageBean<order> getByPage(int id, int currPage)throws Exception;

	List<order> getByAccount(String account)throws Exception;

	order getByCode(String code)throws Exception;

	void delete(String code)throws Exception;

}
