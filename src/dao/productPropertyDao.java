package dao;

import java.util.List;

import domain.productProperty;

public interface productPropertyDao {

	void add(productProperty pp)throws Exception;

	List<productProperty> getByTypeId(String typeId)throws Exception;

	productProperty getById(int id)throws Exception;

	void update(productProperty pp)throws Exception;

	void deleteById(String id)throws Exception;

}
