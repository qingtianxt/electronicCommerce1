package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import constant.constant;
import domain.pageBean;
import domain.product;
import domain.productOption;
import service.productOptionService;
import service.productPropertyService;
import service.productService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;
import utils.UploadUtils;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 跳转到add.jsp
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/admin/product/add.jsp";
	}
	/**
	 * 添加商品信息,一开始准备使用beanutils，但因为需要对很多数据进行处理，不好实现
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productService ps = (productService) BeanFactory.getBean("productService");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		product p = new product();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem items : list) {
				if(items.isFormField()){
					try {
						processform(items,p);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					String name = items.getName();
					
					String realName = UploadUtils.getRealName(name);
					
					String date = DateUtils.getDateStr()+DateUtils.getTimeStr();
					String datename=date+realName;
					String path = this.getServletContext().getRealPath("/product/1");
							
					InputStream is = items.getInputStream();
					FileOutputStream os = new FileOutputStream(new File(path,datename));
					
					IOUtils.copy(is, os);
					os.close();
					is.close();
					items.delete();
					p.setPic("/product/1/"+datename);
				}
			}
			
				if(!(null==p.getId())){
					ps.update(p);
				}
				else{
					//封装参数
					p.setSell_number(0);
					p.setCreate_date(DateUtils.getDate());
					try {
						ps.add(p);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
			} catch (Exception e) {
				e.printStackTrace();
		}
		return "/admin/product/add.jsp?status=1";
	}
	/**
	 * 分页展示所有商品信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		productService ps = (productService) BeanFactory.getBean("productService");
		try {
			pageBean<product> p = ps.getListByPage(currPage);
			p.setPrefixUrl(request.getContextPath()+"/product?method=listByPage");
			p.setAnd(true);
			
			request.setAttribute("productBeans",p.getList());
			request.setAttribute(constant.PAGINGBEAN, p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/list.jsp";
	}
	/**
	 * 展示商品的详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productService ps = (productService) BeanFactory.getBean("productService");
		product p = null;
		try {
			p = ps.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("productBean", p);
		return "/admin/product/details.jsp";
	}
	
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		productService ps = (productService) BeanFactory.getBean("productService");
		try {
			product p = ps.getById(id);
			
			request.setAttribute("productBean", p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/admin/product/add.jsp";
	}
	/**
	 * 添加时表单内容的处理
	 * @param items
	 * @param p
	 * @throws Exception
	 */
		private void processform(FileItem items, product p) throws Exception {
			String name = items.getFieldName();
			
			String value =  new String(items.getString("utf-8"));
			switch (name){
			case "id":
				if(!"".equals(value)){
					int id = StringUtil.StringToInt(value);
					p.setId(id);
				}
				break;
			case "name":
				p.setName(value);
				break;
			case "productTypeId":
				if(p.getProduct_type_id()==null){
					p.setProduct_type_id(StringUtil.StringToInt(value));
				}else{
					p.setProduct_type_id(Math.max(p.getProduct_type_id(), StringUtil.StringToInt(value)));
				}
					break;
			case "original_price":
				p.setOriginal_price(StringUtil.strToFlo(value));
				break;
			case "price":
				p.setPrice(StringUtil.strToFlo(value));
				break;
			case "intro":
				p.setIntro(value);
				break;
			case "number":
				p.setNumber(StringUtil.StringToInt(value));
				break;
			case "option":
				String options = p.getProduct_properties();
				if(options==null){
					p.setProduct_properties(value);
				}else{
					p.setProduct_properties(options+","+value);
				}
				break;
			default:
				break;
			}
		
		}
		/**
		 * 根据id删除信息
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			productService ps = (productService) BeanFactory.getBean("productService");
			try {
				ps.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/product?method=listByPage&currPage=1");
			return null;
		}
		/**
		 * 通过id分类获取商品信息
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String sort (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sortId = request.getParameter("id");
			productService ps = (productService) BeanFactory.getBean("productService");
			try {
				List<product> p =ps.getBySortId(sortId);
				request.setAttribute(constant.PRODUCTBEANS, p);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/front/productShow/list.jsp";
		}
		public String info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			productService ps = (productService) BeanFactory.getBean("productService");
			productOptionService po = (productOptionService) BeanFactory.getBean("productOptionService");
			productPropertyService pp = (productPropertyService) BeanFactory.getBean("productPropertyService");
			List<productOption> list =new ArrayList<>();
			try {
				product p = ps.getById(id);
				
				 String[] split = p.getProduct_properties().split(",");
				 for (String option : split) {
					 productOption op = po.getByOptionId(option);
					 op.setProductPropertyBean(pp.getById(op.getProduct_type_property_id()));
					 list.add(op);
				}
				 p.setProductOptionBeans(list);
				
				 request.setAttribute(constant.PRODUCTBEAN,p);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/front/productShow/info.jsp";
		}
		
		public String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String key = request.getParameter("key");
			productService ps =(productService) BeanFactory.getBean("productService");
			try {
				List<product> list =ps.search(key);
				request.setAttribute(constant.PRODUCTBEANS, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/front/productShow/list.jsp";
		}
}
