package cn.zhaoxiaoxue.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.zhaoxiaoxue.core.util.PageResult;

public interface BaseDao<T> {
	public void save(T entity);
	public void update(T entity);
	public void delete(Serializable id);
	
	public T findById(Serializable id);
	public List<T> findAll();
	//制定查询条件查找
	public List<T> findObjects(String hql,List params);
	//制定请求的页数和每页数量获取分页
	public PageResult getPageResult(int pageNo,int pageSize,Map<String,Object>... condition);
}
