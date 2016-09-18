package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	public List<User> findByName(String name);
	public List<User> findByAccount(String account);
	
	//�����û���ɫ
	public void saveUserRole(UserRole userRole);
	//�����û�id��ȡuser��userrole
	public List<UserRole> findUserRoleById(String userId);
	//�����û�idɾ����Ӧ��userrole
	public void deleteUserRoleById(String userId);
}
