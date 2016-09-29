package cn.zhaoxiaoxue.nsfw.service;

import java.util.List;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.nsfw.entity.Role;

public interface RoleService extends BaseService<Role> {
	
	public List<Role> findByName(String name);
}
