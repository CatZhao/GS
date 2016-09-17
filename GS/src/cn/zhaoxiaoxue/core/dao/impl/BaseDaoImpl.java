package cn.zhaoxiaoxue.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import cn.zhaoxiaoxue.core.dao.BaseDao;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	Class<T> clazz;
	
	@Resource
	private SessionFactory sessionFactory;
	
	//提供getSessionFactory方法，供子类扩展功能使用
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	
	//构造的时候获取T
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
		
		//获取sessionFactory;
		//this.getClass().g
	}
	
	@Override
	public void save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
		
	}

	@Override
	public void delete(Serializable id) {
		T entity = findById(id);
		if(entity != null)
		sessionFactory.getCurrentSession().delete(entity);
		
	}

	@Override
	public T findById(Serializable id) {
		
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		
		return sessionFactory.getCurrentSession().createQuery("from "+clazz.getSimpleName()).list();
	}
	

}
