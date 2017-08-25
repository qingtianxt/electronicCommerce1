package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.productPropertyDao;
import domain.productProperty;
import utils.DataSourceUtils;

public class productPropertyDaoImpl implements productPropertyDao {
/**
 * ÃÌº” Ù–‘
 */
	@Override
	public void add(productProperty pp) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="insert into product_type_property(name,product_type_id,sort,create_date) values(?,?,?,?)";
		qr.update(sql,pp.getName(),pp.getProduct_type_id(),pp.getSort(),pp.getCreate_date());
	}

	@Override
	public List<productProperty> getByTypeId(String typeId) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from  product_type_property where product_type_id=?";
		
		return qr.query(sql, new BeanListHandler<>(productProperty.class),typeId);
	}

	@Override
	public productProperty getById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from  product_type_property where id=?";
		
		return qr.query(sql, new BeanHandler<>(productProperty.class),id);
	}

	@Override
	public void update(productProperty pp) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "update product_type_property set name=?,product_type_id=? where id =?";
		qr.update(sql,pp.getName(),pp.getProduct_type_id(),pp.getId());
	}

	@Override
	public void deleteById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "delete from product_type_property where id =?";
		qr.update(sql,id);
	}


}
