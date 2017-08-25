package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import domain.productProperty;
import domain.productType;
import service.productPropertyService;
import service.productTypeService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;
import utils.UUIDUtils;

/**
 * Servlet implementation class ProductPropertyServlet
 */
public class ProductPropertyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

  /**
   * 携带父类id是0的分类信息跳转到添加页面
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status");
		
		productTypeService ps = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			List<productType> list = ps.getByParentId(0);
			
			request.setAttribute("productTypeList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(status)){
			return "/admin/product/property/add.jsp";
		}else{
			return "/admin/product/property/add.jsp?status="+status;
		}
		
	}
	/**
	 * 添加或者修改属性
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String[] productTypeId = request.getParameterValues("productTypeId");
		String id = request.getParameter("id");
		int id1 = 0;
		for (String ids : productTypeId) {
			id1=Math.max(id1, StringUtil.StringToInt(ids));
		}
		productProperty pp = new productProperty();
		pp.setName(name);
		pp.setProduct_type_id(id1);
		
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		
		if("".equals(id)){//说明是添加操作
			pp.setSort(UUIDUtils.getId());
			pp.setCreate_date(DateUtils.getDate());
			try {
				ps.add(pp);
				response.sendRedirect(request.getContextPath()+"/productProperty?method=addUI");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			pp.setId(StringUtil.StringToInt(id));
			try {
				ps.update(pp);
				response.sendRedirect(request.getContextPath()+"/productProperty?method=listUI&status=1");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
		
	}
	
	/**
	 * 根据分类id获取分类的属性
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getProperty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeId = request.getParameter("id");
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		try {
			List<productProperty> list  = ps.getByTypeId(typeId);
			response.setCharacterEncoding("utf-8");
			String json = JSON.toJSONString(list);
			
			PrintWriter out  = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 携带分类数据跳转到list.jsp
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status");
		
		productTypeService ps = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			List<productType> list = ps.getByParentId(0);
			
			request.setAttribute("productTypeList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(status)){
			return "/admin/product/property/list.jsp";
		}else{
			return "/admin/product/property/list.jsp?status="+status;
		}
		
	}
	/**
	 * 查询出修改的属性的内容，跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		productTypeService ps1 = (productTypeService) BeanFactory.getBean("productTypeService");
		
		try {
			productProperty pd = ps.getById(id);
			
			request.setAttribute("productPropertyBean", pd);
			
			List<productType> list = ps1.getByParentId(0);
			
			request.setAttribute("productTypeList", list);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/property/add.jsp";
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productPropertyService ps = (productPropertyService) BeanFactory.getBean("productPropertyService");
		try {
			
			ps.deleteById(id);
			
			
			productTypeService ps1 = (productTypeService) BeanFactory.getBean("productTypeService");
			List<productType> list = ps1.getByParentId(0);
			request.setAttribute("productTypeList", list);
			response.sendRedirect(request.getContextPath()+"/productProperty?method=listUI&status=2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
