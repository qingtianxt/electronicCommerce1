package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.productOptionDao;
import domain.productOption;
import utils.DataSourceUtils;
import utils.DateUtils;

public class productOptionDaoImpl implements productOptionDao {
/**
 * 添加一条属性的选项信息
 */
	@Override
	public void add(productOption po) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "insert into product_type_property_option(name,product_type_property_id,sort,create_date) values(?,?,?,?)";
		qr.update(sql, po.getName(),po.getProduct_type_property_id(),po.getSort(),po.getCreate_date());
	}

	@Override
	public List<productOption> getByPropertyId(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from product_type_property_option where product_type_property_id=?";
		return qr.query(sql, new BeanListHandler<>(productOption.class),id);
	}

	@Override
	public productOption getByOptionId(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from product_type_property_option where id=?";
		
		return qr.query(sql, new BeanHandler<>(productOption.class),id);
	}

	@Override
	public void update(productOption po) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "update product_type_property_option set name=?,product_type_property_id=? where id=?";
		qr.update(sql,po.getName(),po.getProduct_type_property_id(),po.getId());
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "delete from product_type_property_option where id=?";
		qr.update(sql,id);
	}

}
