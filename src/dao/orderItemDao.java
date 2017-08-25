package dao;

import java.util.List;

import domain.OrderProductBean;

public interface orderItemDao {

	void add(OrderProductBean bean)throws Exception;

	void update(OrderProductBean bean)throws Exception;

	List<OrderProductBean> getByCreate_date(String create_date)throws Exception;

	List<OrderProductBean> getByOrderId(Integer id)throws Exception;

	void delete(Integer id)throws Exception;

}
