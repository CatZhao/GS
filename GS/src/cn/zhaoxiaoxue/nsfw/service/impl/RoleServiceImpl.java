package cn.zhaoxiaoxue.nsfw.service.impl;

import java.util.List;

import cn.zhaoxiaoxue.core.service.impl.BaseServiceImpl;
import cn.zhaoxiaoxue.nsfw.dao.RoleDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;	
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}	

	@Override
	public List<Role> findByName(String name) {
		
		return roleDao.findByName(name);
	}
	
}
