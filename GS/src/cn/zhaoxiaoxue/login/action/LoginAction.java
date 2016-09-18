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
	
	//��¼ϵͳ���������û���Ϣ������Ȩ�޵�session��
	public String login(){		
		if(user != null ){
			String account = user.getAccount();
			String pwd = user.getPassword();
			if(!StringUtils.isBlank(account) && !StringUtils.isBlank(pwd)){
				//�ж��ʺź������Ƿ�ƥ�����ݿ�
				user = userService.findByAccountAndPwd(account, pwd);
				if(user != null){
					//��¼�ɹ������û�Ȩ��Ҳ��װ��user�﹩Ȩ����֤
					user.setUserRoles(userService.findUserRolesByUserId(user.getId()));
					
					//���û���Ϣ���浽session
					ServletActionContext.getRequest().getSession().setAttribute(Constant.Login_USER, user);
					//��ת��home
					return "home";
				}else{
					loginResult = "�˻��������벻��ȷ";	
				}
			}else{
				loginResult = "�������˻�������";	
			}			
		}else{
			loginResult = "�������˻�������";			
		}
		return "loginUI";
	}
	
	//�ǳ�ϵͳ
	public String logout(){
		//���session��ĵ�¼�û���Ϣ����
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.Login_USER);
		return "loginUI";
	}
	
	public String noPermissionUI(){
		this.getClass().getFields();
		return "noPermissionUI";
	}
	
	
	//get/set����
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
