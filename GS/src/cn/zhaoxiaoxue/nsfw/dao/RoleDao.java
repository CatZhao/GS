package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	public void cleanPrivilegeOfRole(String id);
	//�����ý�ɫ����ģ������
	public List<Role> findByName(String name);
}
