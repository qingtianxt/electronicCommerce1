package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.cart;
import domain.cartItem;
import domain.product;
import service.productService;
import utils.BeanFactory;
import utils.StringUtil;

/**
 * 购物车模块
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 获取购物车
	 * @param request
	 * @return
	 */
	public cart getCart(HttpServletRequest request){
		cart c =(cart) request.getSession().getAttribute(constant.SESSION_CART);
		if(c == null){
			c = new cart();
			request.getSession().setAttribute(constant.SESSION_CART, c);
		}
		return c;
	}
/**
 * 添加内容到购物车
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String num = request.getParameter("num");
		productService ps = (productService) BeanFactory.getBean("productService");
		try {
			product p = ps.getById(productId);
			
			cartItem item = new cartItem(p, StringUtil.StringToInt(num));
			cart cart = getCart(request);
			cart.add2Cart(item);
			
			PrintWriter out = response.getWriter();
			out.println("data");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 跳转到购物车页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cart cart = getCart(request);
		return "/front/shopping/cart.jsp";
	
	}
	/**
	 * 删除购物车项
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		cart cart = getCart(request);
		cart.removeFromCart(StringUtil.StringToInt(productId));
		response.sendRedirect(request.getContextPath()+"/cart?method=toCart");
		return null;
	}
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cart cart = getCart(request);
		cart.clearCart();
		response.sendRedirect(request.getContextPath()+"/cart?method=toCart");
		return null;
	}
}
