package service.impl;

import java.util.List;

import dao.orderItemDao;
import dao.productDao;
import domain.OrderProductBean;
import service.orderItemService;
import utils.BeanFactory;

public class orderItemServiceImpl implements orderItemService{

	@Override
	public List<OrderProductBean> getByOrderId(int id) throws Exception {
		orderItemDao od =(orderItemDao) BeanFactory.getBean("orderItemDao");
		productDao pd =(productDao) BeanFactory.getBean("productDao");
		List<OrderProductBean>list = od.getByOrderId(id);
		for (OrderProductBean o : list) {
			o.setProductBean(pd.getById(""+o.getProduct_id()));
		}
		return list;
	}

}
