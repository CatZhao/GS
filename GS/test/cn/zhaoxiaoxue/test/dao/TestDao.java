package cn.zhaoxiaoxue.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.zhaoxiaoxue.test.entity.HibernateTest;


public class TestDao  {
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void save(HibernateTest test){
		sessionFactory.getCurrentSession().save(test);
	}
}
