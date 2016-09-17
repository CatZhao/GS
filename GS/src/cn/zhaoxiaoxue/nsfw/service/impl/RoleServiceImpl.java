package cn.zhaoxiaoxue.nsfw.service.impl;

import java.util.List;

import cn.zhaoxiaoxue.nsfw.dao.RoleDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
		
	}

	@Override
	public void update(Role role) {
		//先把角色权限清空，再写入新的权限		
		roleDao.cleanPrivilegeOfRole(role.getRoleId());
		roleDao.update(role);
		
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
		
	}

	@Override
	public Role findById(String id) {
		return roleDao.findById(id);
		
	}

	@Override
	public List<Role> findByName(String name) {
		
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> findAll() {
		
		return roleDao.findAll();
	}
	
}
