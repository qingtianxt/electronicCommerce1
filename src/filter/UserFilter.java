package filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.constant;
import domain.user;

public class UserFilter implements Filter {

	private Set<String> urls = new HashSet<String>();

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();// 获取访问地址
		System.out.println(1);
		// 该过滤是处理前台登录的判断
		// 路径为front 前台所有的路径
		if (path.startsWith("/user")) {
			// 有某些地址需要放过，比如登录，注册
			System.out.println(2);
			if (urls.contains(path)) {
				System.out.println(3);
				chain.doFilter(request, response);
			} else {
				user userBean = (user) request.getSession().getAttribute(constant.USERBEAN);
				if (userBean != null) {
					System.out.println(4);
					// 已登录，放过
					// 如果符合业务规则，则放过
					chain.doFilter(request, response);
				} else {
					// 没有登录，跳回登录页面
					response.sendRedirect(request.getContextPath() + "/front/login.jsp");
				}
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urls.add("/front/login.jsp");
		urls.add("/user");
		urls.add("/front/add.jsp");
	}
	
}
