package service;

import java.util.List;

import domain.City;
import domain.Province;
import domain.Region;
import domain.orderAddressBean;
import domain.pageBean;
import domain.user;

public interface orderAddressService {

	List<orderAddressBean> getByUser(user u)throws Exception;

	pageBean<orderAddressBean> getAllByCurrPage(int currPage, int id)throws Exception;

	List<Province> getProvince()throws Exception;

	List<City> getCity(int id)throws Exception;

	List<Region> getRegion(int id)throws Exception;

	void add(orderAddressBean o)throws Exception;

	void updateStatus(int id)throws Exception;
	
}
