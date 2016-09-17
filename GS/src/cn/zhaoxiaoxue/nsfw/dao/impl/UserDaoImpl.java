package cn.zhaoxiaoxue.nsfw.dao.impl;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.impl.BaseDaoImpl;
import cn.zhaoxiaoxue.nsfw.dao.UserDao;
import cn.zhaoxiaoxue.nsfw.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findByName(String name) {
		return getSessionFactory().getCurrentSession()
			.createQuery("from User where name like ?")
			.setParameter(0, "%"+name+"%")
			.list();
	}

	@Override
	public List<User> findByAccount(String account) {
		return getSessionFactory().getCurrentSession()
				.createQuery("from User where account=?")
				.setParameter(0,account)
				.list();
	}

	
	

}
