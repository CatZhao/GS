package cn.zhaoxiaoxue.test.entity;

public class HibernateTest {
	private String test;
	private String id;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "HibernateTest [test=" + test + ", id=" + id + "]";
	}
	public HibernateTest(String test, String id) {
		super();
		this.test = test;
		this.id = id;
	}
	public HibernateTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
