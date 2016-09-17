package cn.zhaoxiaoxue.test.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zhaoxiaoxue.test.dao.TestDao;
import cn.zhaoxiaoxue.test.entity.HibernateTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class TestDao_test extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private TestDao testDao;
	
	@Transactional
	@Rollback(false)
	@Test
	public void test_save(){
		
		HibernateTest test = new HibernateTest();
		test.setTest("test-spring");
		testDao.save(test);
		
	}
	
	@Test
	public void test_expression(){
		int x = 0;
		x = x+5;
		System.out.println(x=4);
	}
}
