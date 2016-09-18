package cn.zhaoxiaoxue.nsfw.dao.impl;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.impl.BaseDaoImpl;
import cn.zhaoxiaoxue.nsfw.dao.UserDao;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;

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

	@Override
	public void saveUserRole(UserRole userRole) {
		getSessionFactory().getCurrentSession()
			.save(userRole);
		
	}

	@Override
	public List<UserRole> findUserRoleById(String userId) {
		
		return getSessionFactory().getCurrentSession()
					.createQuery("from UserRole where id.userId=?")
					.setParameter(0, userId)
					.list();
	}

	@Override
	public void deleteUserRoleById(String userId) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete from UserRole where id.userId=?")
			.setParameter(0, userId)
			.executeUpdate();
		
	}
	
	
	
	

}
