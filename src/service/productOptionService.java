package service;

import java.util.List;

import domain.productOption;

public interface productOptionService {

	void add(productOption po) throws Exception;

	List<productOption> getByPropertyId(String id)throws Exception;

	productOption getByOptionId(String id)throws Exception;

	void update(productOption po)throws Exception;

	void deleteById(String id)throws Exception;

}
