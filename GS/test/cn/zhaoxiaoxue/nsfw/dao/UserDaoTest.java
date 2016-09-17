package cn.zhaoxiaoxue.nsfw.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zhaoxiaoxue.nsfw.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource
	UserDao userDao;
	
	@Transactional
	@Rollback(false)
	@Test
	public void test_save(){
		User user = new User();
		user.setAccount("user1");
		user.setDept("test_dept");
		user.setName("test_name");
		user.setPassword("test_password");
		user.setState("1");
		userDao.save(user);
	}
	

}
