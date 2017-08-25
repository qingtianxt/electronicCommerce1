package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import constant.constant;
import domain.OrderProductBean;
import domain.cart;
import domain.cartItem;
import domain.order;
import domain.orderAddressBean;
import domain.pageBean;
import domain.product;
import domain.user;
import service.orderAddressService;
import service.orderItemService;
import service.orderService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.StringUtil;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * 确认订单
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String makeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user u = (user)request.getSession().getAttribute(constant.USERBEAN);
		if(u==null){
			request.setAttribute(constant.MSG, "您还没有登录");
			return "/front/login.jsp";
		}
		float oSum =0;
		float sum =0;
		cart cart =(cart)request.getSession().getAttribute(constant.SESSION_CART);
		Collection<cartItem> items = cart.getItems();
		for (cartItem c : items) {
			oSum += c.getProduct().getOriginal_price()*c.getNum();
			sum +=c.getProduct().getPrice()*c.getNum();
		}
		orderAddressService os =(orderAddressService) BeanFactory.getBean("orderAddressService");
		try {
			List<orderAddressBean> list = os.getByUser(u);
			request.setAttribute(constant.ADDRESSBEANS, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("oSum", oSum);
		request.setAttribute("sum", sum);
		return "front/shopping/order.jsp";
	}
	public String addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user u = (user)request.getSession().getAttribute(constant.USERBEAN);
		if(u==null){
			request.setAttribute(constant.MSG, "您还没有登录");
			return "/front/login.jsp";
		}
		//获取价格
		String osum = request.getParameter("originalPrice");
		String pri = request.getParameter("price");
		float oSum = StringUtil.strToFlo(osum);
		float sum = StringUtil.strToFlo(pri);
		
		
		cart cart =(cart)request.getSession().getAttribute(constant.SESSION_CART);
		Collection<cartItem> items = cart.getItems();
		
		order o = new order();
		o.setUser_id(u.getId());
		o.setCode(DateUtils.getDateStr()+DateUtils.getTimeStr()+u.getId());
		o.setOriginal_price(oSum);
		o.setPrice(sum);
		
		o.setAddress_id(StringUtil.StringToInt(request.getParameter("addr")));
		o.setPayment_type(StringUtil.StringToInt(request.getParameter("payway")));
		o.setStatus(0);
		o.setCreate_date(DateUtils.getDate());
		
		
		List<OrderProductBean> list =new ArrayList<>();
		for (cartItem item : items) {
			OrderProductBean ob =new OrderProductBean();
			ob.setProductBean(item.getProduct());
			ob.setNumber(item.getNum());
			ob.setOrderBean(o);
			ob.setCreate_date(o.getCreate_date());;
			ob.setProduct_id(item.getProduct().getId());
			ob.setPrice(item.getProduct().getPrice());
			
			list.add(ob);
		}
		o.setOrderProductBeans(list);
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			os.add(o);
			request.getSession().removeAttribute(constant.SESSION_CART);
			request.setAttribute(constant.MSG, "提交订单成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "msg.jsp";
	}
	/**
	 * 分页展示我的所有订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		user u =(user) request.getSession().getAttribute(constant.USERBEAN);
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			pageBean<order> page =os.getByPage(u.getId(),currPage);
			page.setAnd(true);
			page.setPrefixUrl(request.getContextPath()+"/order?method=listByPage");
			request.setAttribute(constant.ORDER_PAGINGBEAN, page);
			request.setAttribute(constant.ORDERBEANS, page.getList());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/front/user/order_list.jsp";
	}
	/**
	 * 获取订单项的详细信息(后台)
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getDetailByid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		orderItemService os =(orderItemService) BeanFactory.getBean("orderItemService");
		try {
			List<OrderProductBean> list =os.getByOrderId(id);
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
	 * 更新订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id")) ;
		int status = StringUtil.StringToInt(request.getParameter("status"));
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			os.updateStatus(id,status);
			response.sendRedirect(request.getContextPath()+"/order?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
