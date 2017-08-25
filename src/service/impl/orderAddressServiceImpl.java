package service.impl;

import java.util.List;

import constant.constant;
import dao.orderAddressDao;
import domain.City;
import domain.Province;
import domain.Region;
import domain.orderAddressBean;
import domain.pageBean;
import domain.user;
import service.orderAddressService;
import service.userService;
import utils.BeanFactory;

public class orderAddressServiceImpl implements orderAddressService {
/**
 * 通过用户获取用户的收货地址
 * @return 
 */
	@Override
	public List<orderAddressBean> getByUser(user u) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		
		List<orderAddressBean> list =od.getByUser(u);
		for (orderAddressBean l : list) {
			Province p = new Province();
			City c= new City();
			Region r =new Region();
			p =od.getByProvinceId(l.getProvince());
			c= od.getByCityId(l.getCity());
			r =od.getByAreaId(l.getRegion());
			l.setProvincename(p.getName());
			l.setCityname(c.getName());
			l.setAreaname(r.getName());
		}
		
		return list;
	}
/**
 * 分页返回收货地址信息
 */
	@Override
	public pageBean<orderAddressBean> getAllByCurrPage(int currPage, int id) throws Exception {
		orderAddressDao od = (orderAddressDao) BeanFactory.getBean("orderAddressDao");
		userService us =(userService) BeanFactory.getBean("userService");
		int totalCount = od.count(id);
		
		List<orderAddressBean> list = od.getByPage(currPage,id);
		for (orderAddressBean l : list) {
			Province p = new Province();
			City c= new City();
			Region r =new Region();
			p =od.getByProvinceId(l.getProvince());
			c= od.getByCityId(l.getCity());
			r =od.getByAreaId(l.getRegion());
			l.setProvincename(p.getName());
			l.setCityname(c.getName());
			l.setAreaname(r.getName());
		}
		pageBean<orderAddressBean> page = new pageBean<>(list, totalCount, currPage,constant.ADDRESS_PAGE);
		return page;
	}
	/**
	 * 获取省份信息
	 */
	@Override
	public List<Province> getProvince() throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		return od.getByProvince();
		
	}
	/**
	 * 获取城市信息
	 */
	@Override
	public List<City> getCity(int id) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		return od.getCity(id);
	}
	@Override
	public List<Region> getRegion(int id) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		return od.getRegion(id);
	}
	/**
	 * 添加收货地址
	 */
	@Override
	public void add(orderAddressBean o) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		od.add(o);
	}
	/**
	 * 更改默认收货地址
	 */
	@Override
	public void updateStatus(int id) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		od.updateAllStatus();
		od.updateStatus(id);
	}

}
