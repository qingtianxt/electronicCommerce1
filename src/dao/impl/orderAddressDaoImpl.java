package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import constant.constant;
import dao.orderAddressDao;
import domain.City;
import domain.Province;
import domain.Region;
import domain.orderAddressBean;
import domain.pageBean;
import domain.user;
import utils.DataSourceUtils;

public class orderAddressDaoImpl implements orderAddressDao {
/**
 * �����Ʒ��ַ��Ϣ
 */
	@Override
	public void add(orderAddressBean ad) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql  = "insert into user_address(name,province,city,region,address,cellphone,user_id,status,create_date) values (?,?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql,ad.getName(),ad.getProvince(),
				ad.getCity(),ad.getRegion(),ad.getAddress(),ad.getCellphone(),
				ad.getUser_id(),ad.getStatus(),ad.getCreate_date());
	}
/**
 * ͨ���û�id��ȡ�û��Ķ����ջ���ַ
 */
	@Override
	public List<orderAddressBean> getByUser(user u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql =  "select * from user_address where user_id=? order by create_date desc";
		return qr.query(sql, new BeanListHandler<>(orderAddressBean.class),u.getId());
	}
	/**
	 * ͨ��id��ȡʡ��
	 */
	@Override
	public Province getByProvinceId(int province) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from province where id = ?";
		return qr.query(sql, new BeanHandler<>(Province.class),province);
	}
	
	@Override
	public City getByCityId(int city) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from city where id = ?";
		return qr.query(sql, new BeanHandler<>(City.class),city);
		
	}
	
	@Override
	public Region getByAreaId(int region) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from area where id = ?";
		return qr.query(sql, new BeanHandler<>(Region.class),region);
	}
	/**
	 * ͨ����ַid��ȡ�ջ���ַ��Ϣ
	 * @throws Exception 
	 */
	@Override
	public orderAddressBean getById(Integer address_id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_address where id=?";
		return qr.query(sql, new BeanHandler<>(orderAddressBean.class), address_id);
	}
	/**
	 * ��ҳ��ȡ�ջ���ַ
	 */
	@Override
	public List<orderAddressBean> getByPage(int currPage, int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_address where user_id=? order by create_date desc limit ?,?";
		return qr.query(sql, new BeanListHandler<>(orderAddressBean.class), id,(currPage-1)*constant.ADDRESS_PAGE,constant.ADDRESS_PAGE);
	}
	/**
	 * ������û��������ջ���ַ����
	 */
	@Override
	public int count(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user_address where user_id = ?";
		
		return ((Long)qr.query(sql, new ScalarHandler(), id)).intValue();
	}
	/**
	 * ��ȡʡ����Ϣ
	 */
	@Override
	public List<Province> getByProvince() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from province";
		return qr.query(sql, new BeanListHandler<>(Province.class));
	}
	@Override
	public List<City> getCity(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from city where province_id = ?";
		return qr.query(sql, new BeanListHandler<>(City.class),id);
	}
	@Override
	public List<Region> getRegion(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from area where city_id = ?";
		return qr.query(sql, new BeanListHandler<>(Region.class),id);
	}
	/**
	 * �������е��ջ���ַ����Ĭ���ջ���ַ
	 */
	@Override
	public void updateAllStatus() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_address set status=?";
		qr.update(sql, 0);
	}
	/**
	 * ����id����Ĭ���ջ���ַ
	 */
	@Override
	public void updateStatus(int id) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_address set status=? where id =?";
		qr.update(sql, 1,id);
	}

}
