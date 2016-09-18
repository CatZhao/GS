package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	public List<User> findByName(String name);
	public List<User> findByAccount(String account);
	
	//保存用户角色
	public void saveUserRole(UserRole userRole);
	//根据用户id获取user和userrole
	public List<UserRole> findUserRoleById(String userId);
	//根据用户id删除对应的userrole
	public void deleteUserRoleById(String userId);
}
