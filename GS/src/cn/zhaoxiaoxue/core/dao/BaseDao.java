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
	//�ƶ���ѯ��������
	public List<T> findObjects(String hql,List params);
	//�ƶ������ҳ����ÿҳ������ȡ��ҳ
	public PageResult getPageResult(int pageNo,int pageSize,Map<String,Object>... condition);
}
