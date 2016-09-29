package cn.zhaoxiaoxue.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.core.util.PageResult;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	Class<T> clazz;
	
	@Resource
	private SessionFactory sessionFactory;
	
	//�ṩgetSessionFactory��������������չ����ʹ��
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	
	//�����ʱ���ȡT
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
		
		//��ȡsessionFactory;
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


	@Override
	public List<T> findObjects(String hql, List params) {
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i= 0;i<params.size();i++){
			query.setParameter(i, params.get(i));
		}
		return  query.list();		
	}


	@Override
	public PageResult getPageResult( int pageNo,int pageSize,Map<String,Object>... condition) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer("from "+clazz.getSimpleName());
		boolean flag = condition != null && condition.length > 0 && condition[0].size() > 0;
		
		List<Object> params = new ArrayList<Object>();
		if(flag){
			hql.append(" where ");
			for(String key :condition[0].keySet()){
				hql.append(key+" and ");
				params.add(condition[0].get(key));
			}
			hql.delete(hql.lastIndexOf(" and "), hql.length()-1);
		}
		
		Query queryTotalCount = session.createQuery("select count(*) "+hql.toString());
		Query queryList = session.createQuery(hql.toString());
		//���ò���
		if(flag){
			for(int i=0; i<params.size();i++){
				queryTotalCount.setParameter(i, params.get(i));
				queryList.setParameter(i, params.get(i));
			}
		}
		long totalCount = (Long) queryTotalCount.uniqueResult();
		
		if(pageNo < 1){
			pageNo = 1;
			//�����ǰҳ�������ҳ����ʱ�ж���������أ�������������������������������������������
		}
		//hql��ѯ��ҳ����ʹ��limit���ᱨ��ʶ��ֻ��������2��������ʼ�кͲ�ѯ����
		queryList.setFirstResult((pageNo-1)*pageSize);
		queryList.setMaxResults(pageSize);
		
		List items = queryList.list();
		return new PageResult(totalCount, pageNo, pageSize, items);
	}
	

}
