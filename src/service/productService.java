package service;

import java.util.List;

import domain.pageBean;
import domain.product;

public interface productService {

	void add(product p)throws Exception;

	pageBean<product> getListByPage(int currPage)throws Exception;

	product getById(String id)throws Exception;

	void update(product p)throws Exception;

	void delete(String id)throws Exception;

	List<product> getBySortId(String sortId)throws Exception;

	List<product> search(String key)throws Exception;

}
