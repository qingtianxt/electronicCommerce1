package service;

import java.util.List;

import domain.productType;

public interface productTypeService {

	void add(productType pd) throws Exception;

	List<productType> getByParentId(int parentId)throws Exception;

	productType getById(int id)throws Exception;

	void updateById(int id, productType pd)throws Exception;

	void deleteById(int id)throws Exception;

	List<productType> getAllByTypeId(int id)throws Exception;

	List<productType> getBycache0()throws Exception;//获取所有最高级分类;

}
