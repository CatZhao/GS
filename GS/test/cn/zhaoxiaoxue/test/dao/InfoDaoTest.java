package cn.zhaoxiaoxue.test.dao;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zhaoxiaoxue.nsfw.dao.InfoDao;
import cn.zhaoxiaoxue.nsfw.entity.Info;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class InfoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private InfoDao infoDao;
	
	
	@Test
	public void testFindByTitle() {
		fail("Not yet implemented");
	}

	@Transactional
	@Rollback(false)
	@Test
	public void testSave() {
		Info info = new Info();
		info.setTitle("test");
		info.setContent("test");
		info.setCreater("zhaoxiaoxue");
		info.setCreateTime(new Timestamp(new Date().getTime()));
		info.setMemo("test");
		info.setSource("test");
		info.setState(Info.INFO_STATE_PUBLIC);
		info.setType(Info.INFO_TYPE_TZGG);
		
		infoDao.save(info);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
