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
 * ͨ���û���ȡ�û����ջ���ַ
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
 * ��ҳ�����ջ���ַ��Ϣ
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
	 * ��ȡʡ����Ϣ
	 */
	@Override
	public List<Province> getProvince() throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		return od.getByProvince();
		
	}
	/**
	 * ��ȡ������Ϣ
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
	 * ����ջ���ַ
	 */
	@Override
	public void add(orderAddressBean o) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		od.add(o);
	}
	/**
	 * ����Ĭ���ջ���ַ
	 */
	@Override
	public void updateStatus(int id) throws Exception {
		orderAddressDao od =(orderAddressDao) BeanFactory.getBean("orderAddressDao");
		od.updateAllStatus();
		od.updateStatus(id);
	}

}
