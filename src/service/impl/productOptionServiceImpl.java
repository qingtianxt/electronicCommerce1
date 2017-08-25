package service.impl;

import java.util.List;

import dao.productOptionDao;
import domain.productOption;
import service.productOptionService;
import service.productPropertyService;
import utils.BeanFactory;
import utils.StringUtil;

public class productOptionServiceImpl implements productOptionService{
/**
 * 添加选项操作
 */
	@Override
	public void add(productOption po) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		 pd.add(po);
	}
/**
 * 根据属性id获取属性的选项信息
 */
	@Override
	public List<productOption> getByPropertyId(String id) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		List<productOption> p = pd.getByPropertyId(id);
		for (productOption list : p) {
			list.setProductPropertyBean(ps.getById(StringUtil.StringToInt(id)));
		}
		return p;
	}
	/**
	 * 根据选项id获取选项的信息
	 */
	@Override
	public productOption getByOptionId(String id) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		
		return pd.getByOptionId(id);
	}
	/**
	 * 修改选项信息
	 */
	@Override
	public void update(productOption po) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		pd.update(po);
	}
	@Override
	public void deleteById(String id) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		pd.delete(id);
	}
	
}
