package cn.zhaoxiaoxue.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.zhaoxiaoxue.core.util.PageResult;

public interface BaseService<T> {
		//����
		public void save(T entity);
		//�޸�
		public void update(T entity);
		//����idɾ��
		public void delete(Serializable id);
		
		//����id����
		public T findById(Serializable id);
		//��������
		public List<T> findAll();
		
		/**
		 * ��ȡ��ҳ
		 * @param pageNo �����ҳ���������С��1�����1����pageNo
		 * @param pageSize ÿҳ��ʾ������
		 * @param condition ����в�ѯ����������ģ���������֣����ڴ˴����һ��Map;
		 * 		key��"name like ?",value��hql���õ�parameter,����"%"+name+"%";
		 * 		������map����Ӷ����ֵ�����ö����ѯ����;
		 * 		�����������з�ҳ����Ҫ���������;
		 * @return PageResult 
		 */
		public PageResult getPageResult(int pageNo,int pageSize,Map<String,Object>... condition);
}
