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
 * �����Ʒ����
 */
	@Override
	public void add(productType pd) throws Exception {
		productTypeDao pt = (productTypeDao) BeanFactory.getBean("productTypeDao");
		pt.add(pd);
		//�������������
		CacheManager cm = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//��ȡָ���Ļ���
		Cache cache = cm.getCache("categoryCache");
		//��ջ���
		cache.remove("clist");
	}
/**
 *��ȡ������Ϣ
 */

@Override
public List<productType> getByParentId(int parentId) throws Exception {
	productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
	return pd.getByParentId(parentId);
}
/**
 * ����id��ȡ������Ϣ
 */
	@Override
	public productType getById(int id) throws Exception {
		productTypeDao pd = (productTypeDao) BeanFactory.getBean("productTypeDao");
		return pd.getById(id);
	}
	/**
	 * ���ݷ���id�޸ķ���
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
	 * ɾ����ʱ����Ҫɾ���������ԣ�Ȼ���ٿ����Ƿ����ӷ��࣬����У���ɾ�����������ӷ���
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
		
		//���Ҫɾ����id�������࣬����Ҫ�ݹ�ɾ��������������
		
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
	 * ��ȡ������༰�����������
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
	 * ͨ�������ȡ��߼�����
	 */
	@Override
	public List<productType> getBycache0() throws Exception {
		//�������������
		CacheManager cacheManager = CacheManager.create(productTypeServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml") );
		//��ȡָ���Ļ���
		Cache cache = cacheManager.getCache("categoryCache");
		//ͨ�������ȡ���� ��cache����һ��map����
		Element element = cache.get("clist");
		List<productType> list = null;
		if(element==null){
			//�����ݿ��л�ȡ
			productTypeDao pd =(productTypeDao) BeanFactory.getBean("productTypeDao");
			list = pd.getByParentId(0);
			cache.put(new Element("clist", list));
			System.out.println("������û�����ݣ��Ѵ������л�ȡ");
		}else{
			list = (List<productType>) element.getObjectValue();
			
			System.out.println("������������");
			
		}
		
		return list;
	}
	
}
