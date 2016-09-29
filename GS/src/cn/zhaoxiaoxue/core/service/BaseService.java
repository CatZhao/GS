package cn.zhaoxiaoxue.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.zhaoxiaoxue.core.util.PageResult;

public interface BaseService<T> {
		//新增
		public void save(T entity);
		//修改
		public void update(T entity);
		//根据id删除
		public void delete(Serializable id);
		
		//根据id查找
		public T findById(Serializable id);
		//查找所有
		public List<T> findAll();
		
		/**
		 * 获取分页
		 * @param pageNo 请求的页数，若入参小于1，则把1赋给pageNo
		 * @param pageSize 每页显示的数量
		 * @param condition 如果有查询条件，例如模糊查找名字，则在此处添加一个Map;
		 * 		key是"name like ?",value是hql设置的parameter,比如"%"+name+"%";
		 * 		可以在map中添加多个键值对设置多个查询条件;
		 * 		从所有数据中分页则不需要传这个参数;
		 * @return PageResult 
		 */
		public PageResult getPageResult(int pageNo,int pageSize,Map<String,Object>... condition);
}
