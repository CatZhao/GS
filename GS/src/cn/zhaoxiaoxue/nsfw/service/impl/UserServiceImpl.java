package cn.zhaoxiaoxue.nsfw.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.zhaoxiaoxue.core.service.impl.BaseServiceImpl;
import cn.zhaoxiaoxue.core.util.ExcelUtil;
import cn.zhaoxiaoxue.nsfw.dao.UserDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;
import cn.zhaoxiaoxue.nsfw.entity.UserRoleId;
import cn.zhaoxiaoxue.nsfw.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	//spring注入userDao
	private UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		List<User> userList = ExcelUtil.importUserExcel(userExcel, userExcelFileName);
		if(userList != null && userList.size() > 0){
			for(User user : userList){
				userDao.save(user);
			}
		}
	}

	@Override
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(userList, outputStream);
	}

	@Override
	public List<User> findByName(String name) {
		String hql = "from User where name like ?";
		List params = new ArrayList();
		params.add("%"+name+"%");
		return userDao.findObjects(hql, params);
	}
	
	@Override
	public boolean isAccountAvailable(User user) {
		boolean result = true;
		List<User> userList = userDao.findByAccount(user.getAccount());
		
		if(userList != null && userList.size() > 0){
			//考虑修改用户时，未修改帐号名的情况
			//即：数据库中存在1个此账户名，且对应的id和传入的用户id一样
			if(user.getId() != null && userList.size() == 1 && userList.get(0).getId().equals(user.getId())){
				return true;
			}
			result = false;
		}
		return result;
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		save(user);
		//hibernate特性：存储user后，自动把新生成的userId修改到user中
		saveUserRole(user,roleIds);
		
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		// 先清空原来的userrole
		userDao.deleteUserRoleById(user.getId());
		
		update(user);
		
		//保存更改后的userrole
		saveUserRole(user,roleIds);
	}
	
	//保存用户角色
	protected void saveUserRole(User user,String... roleIds){
		if(roleIds != null && roleIds.length > 0){
			for(String roleId : roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public List<UserRole> findUserRolesByUserId(String id) {
		
		return userDao.findUserRoleById(id);
	}

	@Override
	public User findByAccountAndPwd(String account, String pwd) {
		User user = null;
		
		//根据账户名从数据库查找用户
		List<User> list = userDao.findByAccount(account);
		
		//已知账户名唯一，如果查找到用户，则判断密码是否正确，如果正确，返回这个用户		
		if(list != null && list.size() > 0){			
			if(pwd.equals(list.get(0).getPassword())){
				user = list.get(0);
			}
		}
		return user;
	}

}
