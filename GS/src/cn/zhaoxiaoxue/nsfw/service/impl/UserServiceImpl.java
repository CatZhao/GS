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
	
	//springע��userDao
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
			//�����޸��û�ʱ��δ�޸��ʺ��������
			//�������ݿ��д���1�����˻������Ҷ�Ӧ��id�ʹ�����û�idһ��
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
		//hibernate���ԣ��洢user���Զ��������ɵ�userId�޸ĵ�user��
		saveUserRole(user,roleIds);
		
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		// �����ԭ����userrole
		userDao.deleteUserRoleById(user.getId());
		
		update(user);
		
		//������ĺ��userrole
		saveUserRole(user,roleIds);
	}
	
	//�����û���ɫ
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
		
		//�����˻��������ݿ�����û�
		List<User> list = userDao.findByAccount(account);
		
		//��֪�˻���Ψһ��������ҵ��û������ж������Ƿ���ȷ�������ȷ����������û�		
		if(list != null && list.size() > 0){			
			if(pwd.equals(list.get(0).getPassword())){
				user = list.get(0);
			}
		}
		return user;
	}

}
