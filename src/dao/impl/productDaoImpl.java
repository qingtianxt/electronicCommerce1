package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.productDao;
import domain.product;
import utils.DataSourceUtils;

public class productDaoImpl implements productDao {

	@Override
	public void add(product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "insert into product(name,original_price,price,"
				+ "intro,product_type_id,number,"
				+ "sell_number,pic,product_properties,create_date) values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, p.getName(),p.getOriginal_price(),p.getPrice(),
				p.getIntro(),p.getProduct_type_id(),p.getNumber(),
				p.getSell_number(),p.getPic(),p.getProduct_properties(),p.getCreate_date());
	}
/**
 * 分页获取商品信息
 * @throws Exception 
 */
	@Override
	public List<product> getListByPage(int currPage, int productPagesize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		
		return qr.query(sql, new BeanListHandler<>(product.class),(currPage-1)*productPagesize,productPagesize);
	}
	/**
	 * 获取商品的总数量
	 */
	@Override
	public int count() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product";
		
		return ((Long)qr.query(sql, new ScalarHandler())).intValue();
	}
	/**
	 * 通过id获取商品信息
	 */
	@Override
	public product getById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where id=?";
		return qr.query(sql, new BeanHandler<>(product.class), id);
	}
	@Override
	public void update(product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update product set name=?,original_price=?,price=?,"
				+ "intro=?,product_type_id=?,number=?,"
				+ "pic=?,product_properties=? where id=?";
		qr.update(sql,p.getName(),p.getOriginal_price(),p.getPrice(),
				p.getIntro(),p.getProduct_type_id(),p.getNumber(),
				p.getPic(),p.getProduct_properties(),p.getId());
	}
	@Override
	public void delete(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where id = ?";
		qr.update(sql,id);
	}
	@Override
	public List<product> getByTypeId(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where product_type_id = ?";
		
		return qr.query(sql, new BeanListHandler<>(product.class), id);
	}
	
	
	@Override
	public List<product> search(String key) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql  = "select * from product where name like ?";
		
		return qr.query(sql, new BeanListHandler<>(product.class), "%"+key+"%");
	}
	
}
