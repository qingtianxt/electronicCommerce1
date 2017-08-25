package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.productType;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import service.productTypeService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;
import utils.UUIDUtils;

/**
 * 商品分类
 */
public class ProductTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * 跳转到商品分类添加页面
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		
		productTypeService pd = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			List<productType> list = pd.getByParentId(0);
			request.setAttribute("productTypeList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(status)){
			return "/admin/product/type/add.jsp";
		}
		else{
			return "/admin/product/type/add.jsp?status="+status;
		}
		
		
	}
	/**
	 * 添加或者修改一个商品分类
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  status = 1;
		productType pd = new productType();
		productTypeService ps  = (productTypeService) BeanFactory.getBean("productTypeService");
		String id = request.getParameter("id");
		String[] pareId=request.getParameterValues("parentId");
		int parentId=0;
		for (String parId : pareId) {
			parentId=Math.max(parentId, StringUtil.StringToInt(parId));
		}
		
		try {
			BeanUtils.populate(pd, request.getParameterMap());
			pd.setParentId(parentId);
			if("".equals(id)){
				String sort = UUIDUtils.getId();
				String date = DateUtils.getDate();
				
				pd.setSort(sort);
				pd.setCreate_date(date);
				
				
				ps.add(pd);
				response.sendRedirect(request.getContextPath()+"/productType?method=addUI&id=0&status=1");
				
			}else{
				int id1= StringUtil.StringToInt(id);
				ps.updateById(id1,pd);
				response.sendRedirect(request.getContextPath()+"/productType?method=list&id=0&status=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status=2;
		}
		return null;
	} 
	/**
	 * 获取分类信息ajax传过来
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int parentId = StringUtil.StringToInt(request.getParameter("id"));
		productTypeService pd = (productTypeService) BeanFactory.getBean("productTypeService");
		List<productType> list = null;
		try {
			list =pd.getByParentId(parentId);
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(JSONArray.fromObject(list));
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 获取自己和他的所有子分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status");
		
		//需要获取他的父类，和他的所有子类
		int id = StringUtil.StringToInt(request.getParameter("id"));
		productTypeService ps = (productTypeService) BeanFactory.getBean("productTypeService");
		productType pd = new productType();
		//如果父类id是0，就不用获取他自己，只获取他的子类
		if(id==0){
			try {
				List<productType> childList = ps.getByParentId(id);
				pd.setChildBeans(childList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				pd = ps.getById(id);
				
				List<productType> childList = ps.getByParentId(id);
				pd.setChildBeans(childList);
				
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("productTypeBean", pd);
		if("".equals(status)){
			return "/admin/product/type/list.jsp";
		}
		else{
			return "/admin/product/type/list.jsp?status=1";
		}
	}
	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		productTypeService ps = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			productType productType = ps.getById(id);
			List<productType> list = ps.getByParentId(0);
			
			request.setAttribute("productTypeBean", productType);
			request.setAttribute("productTypeList", list);
			
			request.getRequestDispatcher("/admin/product/type/add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		productTypeService ps = (productTypeService) BeanFactory.getBean("productTypeService");
		try {
			ps.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/productType?method=list&id=0&status=2");
		return null;
	}
}
