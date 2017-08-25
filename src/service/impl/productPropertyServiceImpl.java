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
 * ���ݷ���id��ȡ��������
 */
	@Override
	public List<productProperty> getByTypeId(String typeId) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		
		return pd.getByTypeId(typeId);
	}
	/**
	 * ��������id ��ȡ������Ϣ
	 */
	@Override
	public productProperty getById(int id) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		return pd.getById(id);
		
	}
	/**
	 * �޸�����
	 */
	@Override
	public void update(productProperty pp) throws Exception {
		productPropertyDao pd = (productPropertyDao) BeanFactory.getBean("productPropertyDao");
		pd.update(pp);
	}
	/**
	 * ����idɾ������,�������������ѡ����Ȱ�ѡ��ɾ������Ȼ����ɾ���������
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
