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
 * ���ѡ�����
 */
	@Override
	public void add(productOption po) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		 pd.add(po);
	}
/**
 * ��������id��ȡ���Ե�ѡ����Ϣ
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
	 * ����ѡ��id��ȡѡ�����Ϣ
	 */
	@Override
	public productOption getByOptionId(String id) throws Exception {
		productOptionDao pd = (productOptionDao) BeanFactory.getBean("productOptionDao");
		
		return pd.getByOptionId(id);
	}
	/**
	 * �޸�ѡ����Ϣ
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
