package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.productPropertyDao;
import dao.productTypeDao;
import domain.productProperty;
import domain.productType;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import service.productPropertyService;
import service.productTypeService;
import utils.BeanFactory;

public class productTypeServiceImpl implements productTypeService {
/**
 * 添加商品分类
 */
	@Override
	public void add(productType pd) throws Exception {
		productTypeDao pt = (productTypeDao) BeanFactory.getBean("productTypeDao");
		pt.add(pd);
		//创建缓存管理器
		CacheManager cm = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//获取指定的缓存
		Cache cache = cm.getCache("categoryCache");
		//清空缓存
		cache.remove("clist");
	}
/**
 *获取分类信息
 */

@Override
public List<productType> getByParentId(int parentId) throws Exception {
	productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
	return pd.getByParentId(parentId);
}
/**
 * 根据id获取分类信息
 */
	@Override
	public productType getById(int id) throws Exception {
		productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
		return pd.getById(id);
	}
	/**
	 * 根据分类id修改分类
	 */
	@Override
	public void updateById(int id,productType pt) throws Exception {
		productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
		pd.updateById(id,pt);
		CacheManager cm = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = cm.getCache("categoryCache");
		cache.remove("clist");
	}
	/**
	 * 删除的时候先要删除他的属性，然后再看他是否有子分类，如果有，先删除他的所有子分类
	 */
	@Override
	public void deleteById(int id)  {
		productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
		
		productPropertyDao po =(productPropertyDao) BeanFactory.getBean("productPropertyDao");
		
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		try {
			List<productProperty> list1 = po.getByTypeId(""+id);
			
			for (productProperty l : list1) {
				ps.deleteById(""+l.getId());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//如果要删除的id还有子类，则需要递归删除他的所有子类
		
		List<productType> list=null;
		try {
			list = pd.getByParentId(id);
			
			for (productType pd1 : list) {
				deleteById(pd1.getId());
			}
			
			pd.deleteById(id);
			CacheManager cm = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
			Cache cache = cm.getCache("categoryCache");
			cache.remove("clist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取这个分类及他的子孙分类
	 */
	@Override
	public List<productType> getAllByTypeId(int id) throws Exception {
		productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
		List<productType> list2 = pd.getByParentId(id);
		List<productType> list = new ArrayList<>();
		
		List<productType> list1 = null;
		if(list!=null){
			for (productType type : list2) {
				list1 = getAllByTypeId(type.getId());
				if(list1!=null){
					list.addAll(list1);
				}
			}
		}
		list.add(pd.getById(id));
		return list;
	}
	/**
	 * 通过缓存获取最高级分类
	 */
	@Override
	public List<productType> getBycache0() throws Exception {
		//创建缓存管理器
		CacheManager cacheManager = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml") );
		//获取指定的缓存
		Cache cache = cacheManager.getCache("categoryCache");
		//通过缓存获取数据 将cache看成一个map集合
		Element element = cache.get("clist");
		List<productType> list = null;
		if(element==null){
			//从数据库中获取
			productTypeDao pd =(productTypeDao) BeanFactory.getBean("productTypeDao");
			list = pd.getByParentId(0);
			cache.put(new Element("clist", list));
			System.out.println("缓存中没有数据，已从数据中获取");
		}else{
			list = (List<productType>) element.getObjectValue();
			
			System.out.println("缓存中有数据");
			
		}
		
		return list;
	}
	
}
