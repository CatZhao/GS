package cn.zhaoxiaoxue.nsfw.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;



public interface UserService extends BaseService<User> {
	
	//�����û���ģ������
	public List<User> findByName(String name);
	
	//�����û��˻�������������ݿ�
	public User findByAccountAndPwd(String account,String pwd);
		
	
	//�ж����ݿ����Ƿ��������û�id��ͬ��ͬ��account
	public boolean isAccountAvailable(User user);
	
	//������user��userRole roleIds�ǿɱ��
	public void saveUserAndRole(User user,String... roleIds);
	
	public void updateUserAndRole(User user,String... roleIds);
	//�����û�id��ȡ�û���Ӧ�Ľ�ɫ
	public List<UserRole> findUserRolesByUserId(String id);
	
	
	//ʹ��excel�����û�
	public void importExcel(File userExcel,String userExcelFileName);
	//�����û���excel
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
}
