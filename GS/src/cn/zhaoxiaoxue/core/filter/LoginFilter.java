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
		//强转request response为httprequest httpresponse，方便使用
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		//获取请求uri,判断，如果是登录操作，则放行
		String uri = request.getRequestURI();
		if(uri.contains("login_")){
			chain.doFilter(request, response);
		}else{
			//如果uri非登录，判断session中有无user信息
			User user = (User) request.getSession().getAttribute(Constant.Login_USER);
			
			//如果有，则已登录，判断uri访问的是否纳税服务板块(其他板块权限此处不验），用户有无对应权限
			if(user != null){
				if(uri.contains("/nsfw")){
					//获取用户的权限，判断是否包含纳税服务
					//获取spring容器，获取permissionCheck对象
					WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
					PermissionCheck check = (PermissionCheck)ac.getBean("permissionCheck");
					if(check.isAccessible(user, "nsfw")){
						chain.doFilter(request, response);	
					}else{
						//如果没有对应权限，跳转到login_nopermission
						response.sendRedirect(request.getContextPath()+"/login_noPermissionUI.action");
					}
				}else{
					chain.doFilter(request, response);	
				}		
			}else{
				//如果无user信息，则未登录，返回登录界面
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
