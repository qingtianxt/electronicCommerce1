package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.adminUser;
import domain.pageBean;
import service.AdminUserService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.MD5Utils;
import utils.StringUtil;

/**
 * Servlet implementation class AdminUserServlet
 */
public class AdminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到/admin/adminUser/add.jsp
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/admin/adminUser/add.jsp";
	}

	/**
	 * 添加或者修改管理员操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String updateId = request.getParameter("updateId");

		// 封装数据
		adminUser au = new adminUser();
		au.setUsername(username);

		String date = DateUtils.getDate();
		String salt = StringUtil.getRandomString(10);
		String md5 = MD5Utils.md5((MD5Utils.md5(password) + salt));
		au.setPassword(md5);
		au.setSalt(salt);

		int status = 0;
		// 调用service方法
		AdminUserService as = (AdminUserService) BeanFactory.getBean("AdminUserService");

		if ("".equals(updateId)) {// 说明是添加操作
			// 先判断是否存在这个用户

			try {
				adminUser ad = as.checkReg(username);
				if (ad != null) {
					return "/admin/adminUser/add.jsp?status=1";// 说明用户已存在
				}
				au.setCreate_date(date);

				as.add(au);
				status = 2;// 添加成功
			} catch (Exception e) {
				e.printStackTrace();
				status = 3;// 添加失败
			}
			return "/admin/adminUser/add.jsp?status=" + status;
		} else {// 说明是修改操作
			try {
				System.out.println(au.toString());
				as.update(au);
				status = 1;
			} catch (Exception e) {
				e.printStackTrace();
				status = 2;
			}
			response.sendRedirect(request.getContextPath() + "/adminUser?method=list&currPage=1&status=" + status);
			return null;
		}

	}

	/**
	 * 展示所有用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = StringUtil.StringToInt(request.getParameter("currPage"));
		String status = request.getParameter("status");

		pageBean<adminUser> bean = null;
		AdminUserService as = (AdminUserService) BeanFactory.getBean("AdminUserService");
		try {
			bean = as.findAllByPage(currPage);

			bean.setPrefixUrl(request.getContextPath() + "/adminUser?method=list");
			bean.setAnd(true);

			request.setAttribute("adminBeans", bean.getList());
			request.setAttribute(constant.PAGINGBEAN, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("".equals(status)) {
			return "/admin/adminUser/listUsers.jsp";
		} else {
			return "/admin/adminUser/listUsers.jsp?status=" + status;
		}
	}

	/**
	 * 根据id查询信息 并跳转到add.jsp
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		AdminUserService as = (AdminUserService) BeanFactory.getBean("AdminUserService");
		adminUser ad = null;
		try {
			ad = as.getById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("updateBean", ad);
		return "/admin/adminUser/add.jsp";
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		AdminUserService as = (AdminUserService) BeanFactory.getBean("AdminUserService");
		int status = 0;
		try {
			as.deleteById(id);
			status = 3;
		} catch (Exception e) {
			e.printStackTrace();
			status = 4;
		}
		response.sendRedirect(request.getContextPath() + "/adminUser?method=list&currPage=1&status=" + status);
		return null;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(username + "    " + password);

		AdminUserService as = (AdminUserService) BeanFactory.getBean("AdminUserService");
		adminUser user = null;
		try {
			user = as.checkReg(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			return "/admin/adminUser/login.jsp?status=1";// 用户名不存在
		} else {
			if (!user.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password) + user.getSalt()))) {
				return "/admin/adminUser/login.jsp?status=2";// 用户名或密码错误
			}
			request.getSession().setAttribute("adminUser", user);

		}
		return "/admin/main.jsp";
	}
	/**
	 *退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	public String end(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();

		return "/admin/adminUser/login.jsp?status=3";
	}
}
