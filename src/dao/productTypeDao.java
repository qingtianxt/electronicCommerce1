package dao;

import java.util.List;

import domain.productType;

public interface productTypeDao {

	void add(productType pd) throws Exception;

	List<productType> getByParentId(int parentId)throws Exception;

	productType getById(int id) throws Exception;

	void updateById(int id, productType pt) throws Exception;

	void deleteById(int id)throws Exception;

}
