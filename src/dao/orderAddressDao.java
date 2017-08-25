package dao;

import java.util.List;

import domain.City;
import domain.Province;
import domain.Region;
import domain.orderAddressBean;
import domain.pageBean;
import domain.user;

public interface orderAddressDao {

	void add(orderAddressBean addressBean)throws Exception;

	List<orderAddressBean> getByUser(user u)throws Exception;
	
	Province getByProvinceId(int province)throws Exception;

	City getByCityId(int city)throws Exception;

	Region getByAreaId(int region)throws Exception;

	orderAddressBean getById(Integer address_id)throws Exception;

	List<orderAddressBean> getByPage(int currPage, int id)throws Exception;

	int count(int id)throws Exception;

	List<Province> getByProvince()throws Exception;

	List<City> getCity(int id)throws Exception;

	List<Region> getRegion(int id)throws Exception;

	void updateAllStatus()throws Exception;

	void updateStatus(int id)throws Exception;

}
