package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.RequestContext;

import com.alibaba.fastjson.JSON;

import domain.productOption;
import domain.productType;
import service.productOptionService;
import service.productTypeService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;
import utils.UUIDUtils;

/**
 * ��Ʒ����ѡ��ģ��
 */
public class ProductOptionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * Я���������ײ���Ϣ��ת�����ҳ��
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productTypeService ps  = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			List<productType> list = ps.getByParentId(0);
			if(list!=null){
				request.setAttribute("productTypeList", list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/admin/product/option/add.jsp";
	}
	/**
	 * ��ҳ�洩��������Ϣ�������ת��add.jsp
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int PropertyId = StringUtil.StringToInt(request.getParameter("PropertyId"));
		
		String id = request.getParameter("id");
		
		productOption po = new productOption();
		po.setName(name);
		po.setProduct_type_property_id(PropertyId);
		productOptionService ps = (productOptionService) BeanFactory.getBean("productOptionService");
		
		
		productTypeService pt  = (productTypeService) BeanFactory.getBean("productTypeService");
		List<productType> list = null;
		try {
			list = pt.getByParentId(0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(list!=null){
			request.setAttribute("productTypeList", list);
		}
		
		
		if("".equals(id)){//��Ӳ���
			
			po.setCreate_date(DateUtils.getDate());
			po.setSort(UUIDUtils.getId());
			
			try {
				ps.add(po);
				
				return "/admin/product/option/add.jsp?status=1";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{//�޸Ĳ���
			po.setId(StringUtil.StringToInt(id));
			try {
				ps.update(po);
				return "/admin/product/option/list.jsp?status=1";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	/**
	 * ���ݷ�������id�������Ե�ѡ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productTypeService pt  = (productTypeService) BeanFactory.getBean("productTypeService");
		List<productType> list= null;
		try {
			list = pt.getByParentId(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list!=null){
			request.setAttribute("productTypeList", list);
		}
		return "/admin/product/option/list.jsp";
	}
	public String showOption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productOptionService ps =(productOptionService) BeanFactory.getBean("productOptionService");
		try {
			List<productOption> pd = ps.getByPropertyId(id);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSONString(pd));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ѯ��ѡ�����Ϣ����ת�����ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productOptionService ps =(productOptionService) BeanFactory.getBean("productOptionService");
		try {
			productOption po = ps.getByOptionId(id);
			request.setAttribute("productOptionBean", po);
			
			productTypeService pt  = (productTypeService) BeanFactory.getBean("productTypeService");
			List<productType> list = pt.getByParentId(0);
			if(list!=null){
				request.setAttribute("productTypeList", list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/option/add.jsp";
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productOptionService ps =(productOptionService) BeanFactory.getBean("productOptionService");
		try {
			ps.deleteById(id);
			
			productTypeService pt  = (productTypeService) BeanFactory.getBean("productTypeService");
			List<productType> list = pt.getByParentId(0);
			if(list!=null){
				request.setAttribute("productTypeList", list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/option/list.jsp?status=2";
	}
}
