package cn.zhaoxiaoxue.nsfw.dao.impl;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.impl.BaseDaoImpl;
import cn.zhaoxiaoxue.nsfw.dao.InfoDao;
import cn.zhaoxiaoxue.nsfw.entity.Info;

public class InfoDaoImpl extends BaseDaoImpl<Info> implements InfoDao {

	@Override
	public List<Info> findByTitle(String key) {
		return this.getSessionFactory().getCurrentSession()
			.createQuery("from Info where title like ?")
			.setParameter(0, "%"+key+"%")
			.list();
	}



}
