package dao;

import java.util.List;

import domain.product;

public interface productDao {

	void add(product p)throws Exception;

	List<product> getListByPage(int currPage, int productPagesize)throws Exception;

	int count()throws Exception;

	product getById(String id)throws Exception;

	void update(product p)throws Exception;

	void delete(String id)throws Exception;

	List<product> getByTypeId(int id)throws Exception;

	List<product> search(String key)throws Exception;

}
