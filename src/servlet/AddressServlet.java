package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import constant.constant;
import domain.City;
import domain.Province;
import domain.Region;
import domain.orderAddressBean;
import domain.pageBean;
import domain.user;
import service.orderAddressService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;

/**
 * Servlet implementation class AddressServlet
 */
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 根据当前页分页显示用户收货地址
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		user u = (user) request.getSession().getAttribute(constant.USERBEAN);
		orderAddressService os =(orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			pageBean<orderAddressBean> page = os.getAllByCurrPage(currPage,u.getId());
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/address?method=listByPage");
			request.setAttribute(constant.ADDRESSBEANS, page.getList());
			request.setAttribute(constant.ADDRESS_PAGINGBEAN, page);
			List<Province> p =os.getProvince();
			request.setAttribute(constant.PROVINCE, p);
			request.getRequestDispatcher("/front/user/address.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String getCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		orderAddressService os = (orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			List<City> list = os.getCity(id);
			response.setCharacterEncoding("utf-8");
			PrintWriter out =response.getWriter();
			out.println(JSONArray.toJSONString(list));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRegion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		orderAddressService os = (orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			List<Region> list = os.getRegion(id);
			response.setCharacterEncoding("utf-8");
			PrintWriter out =response.getWriter();
			out.println(JSONArray.toJSONString(list));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加收货地址
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user u =(user) request.getSession().getAttribute(constant.USERBEAN);
		String name = request.getParameter("name");
		int provinceId = StringUtil.StringToInt(request.getParameter("provinceId"));
		int cityId = StringUtil.StringToInt(request.getParameter("cityId"));
		int regionId = StringUtil.StringToInt(request.getParameter("regionId"));
		String address = request.getParameter("addressinfo");
		String cellphone = request.getParameter("account");
		orderAddressBean o =new orderAddressBean();
		o.setName(name);
		o.setProvince(provinceId);
		o.setCity(cityId);
		o.setRegion(regionId);
		o.setAddress(address);
		o.setCellphone(cellphone);
		o.setUser_id(u.getId());
		o.setStatus(0);
		o.setCreate_date(DateUtils.getDate());
		orderAddressService os =(orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			os.add(o);
			response.sendRedirect(request.getContextPath()+"/address?method=listByPage&currPage=1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * 更新默认的收货地址
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt( request.getParameter("id"));
		orderAddressService os =(orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			os.updateStatus(id);
			response.sendRedirect(request.getContextPath()+"/address?method=listByPage&currPage=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
