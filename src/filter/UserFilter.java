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
		String path = request.getServletPath();// ��ȡ���ʵ�ַ
		System.out.println(1);
		// �ù����Ǵ���ǰ̨��¼���ж�
		// ·��Ϊfront ǰ̨���е�·��
		if (path.startsWith("/user")) {
			// ��ĳЩ��ַ��Ҫ�Ź��������¼��ע��
			System.out.println(2);
			if (urls.contains(path)) {
				System.out.println(3);
				chain.doFilter(request, response);
			} else {
				user userBean = (user) request.getSession().getAttribute(constant.USERBEAN);
				if (userBean != null) {
					System.out.println(4);
					// �ѵ�¼���Ź�
					// �������ҵ�������Ź�
					chain.doFilter(request, response);
				} else {
					// û�е�¼�����ص�¼ҳ��
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
