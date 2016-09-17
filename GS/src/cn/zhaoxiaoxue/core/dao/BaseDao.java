package cn.zhaoxiaoxue.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	public void save(T entity);
	public void update(T entity);
	public void delete(Serializable id);
	
	public T findById(Serializable id);
	public List<T> findAll();
	
}
