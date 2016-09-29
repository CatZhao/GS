package cn.zhaoxiaoxue.nsfw.service;

import java.util.List;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.nsfw.entity.Info;

public interface InfoService extends BaseService<Info> {
	public List<Info> findByTitle(String key);
}
