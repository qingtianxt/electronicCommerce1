package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.order;
import service.orderService;
import utils.BeanFactory;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * ��ת���û�������ѯ
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String adminUserOrderUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/adminUserOrder.jsp";
	}
	/**
	 * ��ѯ���û������ж���������ȥ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("search");
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			List<order> list =os.getByAccount(account);
			
			if(list!=null){
				request.setAttribute(constant.ORDERBEANS, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/adminUserOrder.jsp";
	}
	public String selectOrderUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/orderSelect.jsp";
	}
	public String selectOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			order o =os.getByCode(code);
			request.setAttribute(constant.ORDERBEAN, o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/orderSelect.jsp";
	}
	/**
	 * ��ת����������ɾ��ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String orderDeleteUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/orderDelete.jsp";
	}
	
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		orderService os =(orderService) BeanFactory.getBean("orderService");
		try {
			os.delete(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/orderDelete.jsp";
	}
	
}
