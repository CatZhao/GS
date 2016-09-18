package cn.zhaoxiaoxue.core.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.zhaoxiaoxue.core.constant.Constant;
import cn.zhaoxiaoxue.core.permission.PermissionCheck;

import cn.zhaoxiaoxue.nsfw.entity.User;


public class LoginFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		//ǿתrequest responseΪhttprequest httpresponse������ʹ��
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		//��ȡ����uri,�жϣ�����ǵ�¼�����������
		String uri = request.getRequestURI();
		if(uri.contains("login_")){
			chain.doFilter(request, response);
		}else{
			//���uri�ǵ�¼���ж�session������user��Ϣ
			User user = (User) request.getSession().getAttribute(Constant.Login_USER);
			
			//����У����ѵ�¼���ж�uri���ʵ��Ƿ���˰������(�������Ȩ�޴˴����飩���û����޶�ӦȨ��
			if(user != null){
				if(uri.contains("/nsfw")){
					//��ȡ�û���Ȩ�ޣ��ж��Ƿ������˰����
					//��ȡspring��������ȡpermissionCheck����
					WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
					PermissionCheck check = (PermissionCheck)ac.getBean("permissionCheck");
					if(check.isAccessible(user, "nsfw")){
						chain.doFilter(request, response);	
					}else{
						//���û�ж�ӦȨ�ޣ���ת��login_nopermission
						response.sendRedirect(request.getContextPath()+"/login_noPermissionUI.action");
					}
				}else{
					chain.doFilter(request, response);	
				}		
			}else{
				//�����user��Ϣ����δ��¼�����ص�¼����
				response.sendRedirect(request.getContextPath()+"/login_loginUI.action");
			}
		}		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
