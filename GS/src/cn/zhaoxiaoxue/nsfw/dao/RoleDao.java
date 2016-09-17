package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	public void cleanPrivilegeOfRole(String id);
	//根据用角色名称模糊查找
	public List<Role> findByName(String name);
}
