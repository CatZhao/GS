package cn.zhaoxiaoxue.nsfw.dao;

import java.util.List;

import cn.zhaoxiaoxue.core.dao.BaseDao;
import cn.zhaoxiaoxue.nsfw.entity.Info;

public interface InfoDao extends BaseDao<Info> {
	public List<Info> findByTitle(String key);
}
