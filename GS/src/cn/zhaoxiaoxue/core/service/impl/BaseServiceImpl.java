package cn.zhaoxiaoxue.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.core.util.PageResult;

public class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao baseDao;	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);		
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findById(Serializable id) {
		return (T) baseDao.findById(id);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public PageResult getPageResult(int pageNo, int pageSize,Map<String,Object>... condition) {
		return baseDao.getPageResult(pageNo, pageSize,condition);
	}

}
