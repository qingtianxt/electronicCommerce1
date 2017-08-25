package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import constant.constant;
import domain.pageBean;
import domain.user;
import service.userService;
import utils.BeanFactory;
import utils.DateUtils;
import utils.MD5Utils;
import utils.MailUtils;
import utils.StringUtil;
import utils.UUIDUtils;
import utils.UploadUtils;

/**
 * ǰ̨�û�
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
/**
 * ���ǰ̨�û���Ϣ
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService us = (userService) BeanFactory.getBean("userService");
		
		HashMap<String,Object> map = new HashMap<>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			
			for (FileItem fi : list) {
				if(fi.isFormField()){
					map.put(fi.getFieldName(), fi.getString("utf-8"));
				}else{
					//��������
					String name = fi.getName();
					String realName = UploadUtils.getRealName(name);
					String datename=DateUtils.getDateStr()+DateUtils.getTimeStr()+realName;
					
					String path= request.getServletContext().getRealPath("/product/2");
					InputStream is = fi.getInputStream();
					FileOutputStream os = new FileOutputStream(new File(path,datename));
					IOUtils.copy(is, os);
					os.close();
					is.close();
					fi.delete();
					map.put("pic", "/product/2/"+datename);
				}
			}
			user u = new user();
			BeanUtils.populate(u, map);
			
			if(!(us.getByUserName(u.getAccount())==null)){
				request.setAttribute("msg", "�û����Ѵ���");
				return "/front/add.jsp";
			}
			if("��".equals(map.get("sex"))){
				u.setSex(0);
			}else{
				u.setSex(1);
			}
			u.setStatus(0);
			String salt = StringUtil.getRandomString(10);
			u.setSalt(salt);
			
			u.setPassword(MD5Utils.md5(MD5Utils.md5(u.getPassword())+salt));
			
			u.setCreate_date(DateUtils.getDate());
			u.setCode(UUIDUtils.getCode());
			us.add(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", "��ӳɹ�,��ȥ���伤��");
		return "/front/add.jsp";
	}
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		userService us = (userService) BeanFactory.getBean("userService");
		user u = null;
		try {
			u = us.active(code);
			
			String msg="";
			if(u==null){
				msg="����ʧ�ܣ�������ע��";
			}else{
				msg="����ɹ�";
			}
			request.setAttribute("msg", msg);
			return "msg.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �����ҳ����ת����¼����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/front/login.jsp";
	}
	/**
	 * �ӵ�¼ҳ����ת�����ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/front/add.jsp";
	}
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		userService us = (userService) BeanFactory.getBean("userService");
		try {
			user user = us.getByUserName(account);
			if(user == null){
				request.setAttribute("msg", "�û���������");;//�û���������
			}
			else{
				if(user.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password)+user.getSalt()))){
					request.getSession().setAttribute(constant.USERBEAN, user);
					request.setAttribute("msg", "��½�ɹ�");;//��½�ɹ�
				}else{
					request.setAttribute("msg", "�û��������������");;//�û��������������
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/front/productShow/list.jsp";
	}
	/**
	 * ��ҳչʾ�û���Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String listByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status1");
		int currPage = StringUtil.StringToInt(request.getParameter("currPage")) ;
		userService us = (userService) BeanFactory.getBean("userService");
		try {
			pageBean<user> pb = us.findAllByPage(currPage);
			
			pb.setPrefixUrl(request.getContextPath()+"/user?method=listByPage");
			pb.setAnd(true);
			request.setAttribute(constant.USERBEANS, pb.getList());
			request.setAttribute(constant.PAGINGBEAN, pb);
			if(!(status==null)){
				request.setAttribute(constant.MSG, "�����ɹ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/front/listUsers.jsp";
	}
	/**
	 * ͨ���û��� ��ȡ�û�����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account =request.getParameter("account");
		String status = request.getParameter("status1");
		
		userService us = (userService) BeanFactory.getBean("userService");
		try {
			user u =us.getByUserName(account);
			request.setAttribute(constant.USERBEAN, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!(null==status)){
			request.setAttribute(constant.MSG, "�����ɹ�");	
		}
		return "/front/blockUser.jsp";
	}
	/**
	 * �޸��û�״̬
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = StringUtil.StringToInt(request.getParameter("flag"));
		String account = request.getParameter("account");
		int status = StringUtil.StringToInt(request.getParameter("status"));
		userService us = (userService) BeanFactory.getBean("userService");
		user u = null;
		try {
			u = us.getByUserName(account);
			u.setStatus(status);
			us.update(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag==0){
			response.sendRedirect(request.getContextPath()+"/user?method=listByPage&currPage=1&status1=1");
		}else{
			response.sendRedirect(request.getContextPath()+"/user?method=getByUserName&status1=1&account="+u.getAccount());
			
		}
		return null;
	}
	/**
	 * ��ת��/front/blockUser.jsp�����˻�ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String blockUserUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/front/blockUser.jsp";
	}
	/**
	 * ��ת��/front/user/userinfo.jsp,������ҳ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String userinfoUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/front/user/userinfo.jsp";
	}
	/**
	 * �޸��û��Ļ�����Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringUtil.StringToInt(request.getParameter("id"));
		String account = request.getParameter("account");
		String nickname = request.getParameter("nickname");
		String Npassword = request.getParameter("Npassword");
		String salt = request.getParameter("salt");
		userService us =(userService) BeanFactory.getBean("userService");
		
		
		try {
			user u = us.getById(id);
			u.setId(id);
			u.setAccount(account);
			u.setNickname(nickname);
			u.setPassword(MD5Utils.md5(MD5Utils.md5(Npassword)+salt));
			
			us.update(u);
			u = new user();
			request.getSession().setAttribute(constant.USERBEAN, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/front/main.jsp";
	}
	/**
	 * ע���˻�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		
		return "/front/login.jsp";
	}
	public String updatePic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService us =(userService) BeanFactory.getBean("userService");
		HashMap<String, Object> map = new HashMap<>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for (FileItem fi : list) {
				if(fi.isFormField()){
					map.put(fi.getFieldName(),fi.getString("utf-8"));
				}else{
					String name = fi.getName();
					if("".equals(name)){
						map.put("pic", null);
					}else{
						String realName = UploadUtils.getRealName(name);
						String dateName =DateUtils.getDateStr()+DateUtils.getTimeStr()+realName;
						String path = request.getServletContext().getRealPath("/product/2");
						InputStream is = fi.getInputStream();
						FileOutputStream os = new FileOutputStream(new File(path,dateName));
						IOUtils.copy(is, os);
						os.close();
						is.close();
						fi.delete();
						map.put("pic", "/product/2/"+dateName);
					}
					
				}
				
			}
			
			user u = us.getById(StringUtil.StringToInt((String)map.get("id")));
			
				u.setPic((String) map.get("pic"));
			
			
			us.update(u);
			request.getSession().setAttribute(constant.USERBEAN, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/front/main.jsp";
	}	
	
}
