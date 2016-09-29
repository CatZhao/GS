package cn.zhaoxiaoxue.nsfw.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.zhaoxiaoxue.core.service.impl.BaseServiceImpl;
import cn.zhaoxiaoxue.nsfw.dao.InfoDao;
import cn.zhaoxiaoxue.nsfw.entity.Info;
import cn.zhaoxiaoxue.nsfw.service.InfoService;

public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	//IOCע��infoDao,ͬʱ���ø��෽������basedaoע���ȥ
	private InfoDao infoDao;
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	
	@Override
	public List<Info> findByTitle(String key) {		
		return infoDao.findByTitle(key);
	}
	

}
