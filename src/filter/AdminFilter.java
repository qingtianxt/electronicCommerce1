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
import domain.adminUser;

public class AdminFilter implements Filter{
	private Set<String> urls = new HashSet<String>();

	
	public AdminFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String path = request.getServletPath();
		// 获取访问地址
		// 该过滤是处理后台管理员登录的判断
		if (path.startsWith("/adminUser")) {
			// 有某些地址需要放过，比如登录，注册
			if (urls.contains(path)) {
				arg2.doFilter(request, response);
			} else {
				adminUser adminBean = (adminUser) request.getSession().getAttribute(constant.ADMINUSER);
				if (adminBean != null) {
					// 已登录，放过
					// 如果符合业务规则，则放过
					arg2.doFilter(request, response);
				} else {
					// 没有登录，跳回登录页面
					response.sendRedirect(request.getContextPath() + "/admin/adminUser/login.jsp");
				}
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urls.add("/admin/adminUser/login.jsp");
		urls.add("/admin/adminUser");
		urls.add("/admin/adminUser/add.jsp");
	}
	

}
