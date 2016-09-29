package cn.zhaoxiaoxue.nsfw.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;



public interface UserService extends BaseService<User> {
	
	//根据用户名模糊查找
	public List<User> findByName(String name);
	
	//根据用户账户和密码查找数据库
	public User findByAccountAndPwd(String account,String pwd);
		
	
	//判断数据库中是否存在与该用户id不同的同名account
	public boolean isAccountAvailable(User user);
	
	//保存用user和userRole roleIds是可变参
	public void saveUserAndRole(User user,String... roleIds);
	
	public void updateUserAndRole(User user,String... roleIds);
	//根据用户id获取用户对应的角色
	public List<UserRole> findUserRolesByUserId(String id);
	
	
	//使用excel导入用户
	public void importExcel(File userExcel,String userExcelFileName);
	//导出用户到excel
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
}
