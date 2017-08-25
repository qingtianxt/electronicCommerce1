package dao;

import java.util.List;

import domain.productOption;

public interface productOptionDao {

	void add(productOption po)throws Exception;

	List<productOption> getByPropertyId(String id)throws Exception;

	productOption getByOptionId(String id)throws Exception;

	void update(productOption po)throws Exception;

	void delete(String id)throws Exception;


}
