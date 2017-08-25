package service;

import java.util.List;

import domain.OrderProductBean;

public interface orderItemService {

	List<OrderProductBean> getByOrderId(int id)throws Exception;

}
