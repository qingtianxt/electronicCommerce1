package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.productTypeDao;
import domain.productType;
import utils.DataSourceUtils;

public class productTypeDaoImpl implements productTypeDao {

	@Override
	public void add(productType pd) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  ="insert into product_type(name,parent_id,sort,intro,create_date) values(?,?,?,?,?)";
		qr.update(sql,pd.getName(),pd.getParentId(),pd.getSort(),pd.getIntro(),pd.getCreate_date());
	}
/**
 * 根据父类id获取信息
 */
	@Override
	public List<productType> getByParentId(int parentId) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product_type where parent_id=?";
		
		return qr.query(sql, new BeanListHandler<>(productType.class), parentId);
	}
	/**
	 * 根据id获取分类信息
	 */
	@Override
	public productType getById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product_type where id=?";
		
		return qr.query(sql, new BeanHandler<>(productType.class), id);
	}
	/**
	 * 根据分类id修改分类信息
	 */
	@Override
	public void updateById(int id,productType pt) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product_type set name=?,parent_id=?,intro=? where id=?";
		qr.update(sql, pt.getName(),pt.getParentId(),pt.getIntro(),id);
	}
	@Override
	public void deleteById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product_type where id=?";
		qr.update(sql,id);
	}
}
