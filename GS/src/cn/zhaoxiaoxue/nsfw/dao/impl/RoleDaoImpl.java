package cn.zhaoxiaoxue.nsfw.dao.impl;

import java.io.Serializable;
import java.util.List;

import cn.zhaoxiaoxue.core.dao.impl.BaseDaoImpl;
import cn.zhaoxiaoxue.nsfw.dao.RoleDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void cleanPrivilegeOfRole(String id) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete from RolePrivilege where rolePrivilegeId.role.roleId = ?")
			.setParameter(0, id)
			.executeUpdate();		
	}

	@Override
	public List<Role> findByName(String name) {
		return getSessionFactory().getCurrentSession()
					.createQuery("from Role where name like ?")
					.setParameter(0, "%"+name+"%")
					.list();
	}

}
