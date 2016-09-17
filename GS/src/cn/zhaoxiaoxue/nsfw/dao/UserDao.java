package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.User;

public interface UserDao extends BaseDao<User> {
	public List<User> findByName(String name);
	public List<User> findByAccount(String account);
}
