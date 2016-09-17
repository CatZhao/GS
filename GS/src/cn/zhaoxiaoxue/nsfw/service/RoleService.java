package cn.zhaoxiaoxue.nsfw.service;

import java.util.List;

import cn.zhaoxiaoxue.nsfw.entity.Role;

public interface RoleService {
	public void save(Role role);
	public void update(Role role);
	public void delete(String id);
	
	public Role findById(String id);
	public List<Role> findByName(String name);
	public List<Role> findAll();
}
