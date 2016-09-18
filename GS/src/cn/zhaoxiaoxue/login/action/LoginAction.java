package cn.zhaoxiaoxue.login.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.zhaoxiaoxue.core.constant.Constant;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;
import cn.zhaoxiaoxue.nsfw.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private UserService userService;	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private User user;
	private String loginResult;
	
	
	public String loginUI(){
		return "loginUI";
	}
	
	//登录系统，并保存用户信息（包括权限到session）
	public String login(){		
		if(user != null ){
			String account = user.getAccount();
			String pwd = user.getPassword();
			if(!StringUtils.isBlank(account) && !StringUtils.isBlank(pwd)){
				//判断帐号和密码是否匹配数据库
				user = userService.findByAccountAndPwd(account, pwd);
				if(user != null){
					//登录成功，把用户权限也封装到user里供权限验证
					user.setUserRoles(userService.findUserRolesByUserId(user.getId()));
					
					//把用户信息保存到session
					ServletActionContext.getRequest().getSession().setAttribute(Constant.Login_USER, user);
					//跳转到home
					return "home";
				}else{
					loginResult = "账户名或密码不正确";	
				}
			}else{
				loginResult = "请输入账户和密码";	
			}			
		}else{
			loginResult = "请输入账户和密码";			
		}
		return "loginUI";
	}
	
	//登出系统
	public String logout(){
		//清除session里的登录用户信息即可
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.Login_USER);
		return "loginUI";
	}
	
	public String noPermissionUI(){
		this.getClass().getFields();
		return "noPermissionUI";
	}
	
	
	//get/set方法
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	

}
