package service.impl;

import java.util.List;

import dao.productOptionDao;
import dao.productPropertyDao;
import domain.productOption;
import domain.productProperty;
import service.productPropertyService;
import utils.BeanFactory;

public class productPropertyServiceImpl implements productPropertyService {

	@Override
	public void add(productProperty pp) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		pd.add(pp);
	}
/**
 * 根据分类id获取分类属性
 */
	@Override
	public List<productProperty> getByTypeId(String typeId) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		
		return pd.getByTypeId(typeId);
	}
	/**
	 * 根据属性id 获取属性信息
	 */
	@Override
	public productProperty getById(int id) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		return pd.getById(id);
		
	}
	/**
	 * 修改属性
	 */
	@Override
	public void update(productProperty pp) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		pd.update(pp);
	}
	/**
	 * 根据id删除属性,如果属性下面有选项，则先把选项删除掉，然后在删除这个属性
	 */
	@Override
	public void deleteById(String id) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		
		productOptionDao po = (productOptionDao) BeanFactory.getBean("productOptionDao");
		List<productOption> list = po.getByPropertyId(id);
		for (productOption l : list) {
			po.delete(""+l.getId());
		}
		pd.deleteById(id);
	}
	
}
