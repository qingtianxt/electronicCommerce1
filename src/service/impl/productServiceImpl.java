package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.bcel.classfile.Constant;

import constant.constant;
import dao.productDao;
import dao.productOptionDao;
import domain.adminUser;
import domain.pageBean;
import domain.product;
import domain.productOption;
import domain.productType;
import service.productService;
import service.productTypeService;
import utils.BeanFactory;
import utils.StringUtil;

public class productServiceImpl implements productService{
/**
 * 添加商品信息
 */
	@Override
	public void add(product p) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		pd.add(p);
	}
/**
 * 分页获取商品信息
 */
	@Override
	public pageBean<product> getListByPage(int currPage) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
				
		List<product> p1 =pd.getListByPage(currPage,constant.PRODUCT_PAGESIZE);
		int countNum = pd.count();
		return  new pageBean<>(p1, countNum, currPage, constant.PRODUCT_PAGESIZE);
		
	}
	/**
	 * 通过id获取商品信息
	 */
	@Override
	public product getById(String id) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		productOptionDao po = (productOptionDao) BeanFactory.getBean("productOptionDao");
		product p =pd.getById(id);
		if(!(null==(p.getProduct_properties()))){
			String[] split = p.getProduct_properties().split(",");
			List<productOption> list = new ArrayList<>();
			
			for (String s : split) {
				list.add(po.getByOptionId(s));
			}
			p.setProductOptionBeans(list);
		}
		return p;
	}
	/**
	 * 修改商品信息
	 */
	@Override
	public void update(product p) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		pd.update(p);
	}
	/**
	 * 根据商品id删除商品
	 */
	@Override
	public void delete(String id) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		pd.delete(id);
	}
	/**
	 * 通过分类id分类获取该分类和他下的所有分类商品信息
	 */
	@Override
	public List<product> getBySortId(String sortId) throws Exception {
		productTypeService ps =(productTypeService) BeanFactory.getBean("productTypeService");
		//调用方法获取该分类下的所有分类
		List<productType> list = ps.getAllByTypeId(StringUtil.StringToInt(sortId));
		
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		List<product> p = new ArrayList<>();
		List<product> p1 = new ArrayList<>();
		for (productType type : list) {
			p1= pd.getByTypeId(type.getId());
			p.addAll(p1);
		}
		return p;
	}
	/**
	 * 导航栏搜索，根据输入的关键词进行匹配
	 */
	@Override
	public List<product> search(String key) throws Exception {
		productDao pd = (productDao) BeanFactory.getBean("productDao");
		return pd.search(key);
	}
	
}
