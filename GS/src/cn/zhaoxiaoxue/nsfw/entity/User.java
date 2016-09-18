package cn.zhaoxiaoxue.nsfw.entity;

import java.util.Date;
import java.util.List;

public class User {
	private String id;
	private String name;
	private String account;
	private String dept;
	private String password;
	private String image;
	private boolean gender;
	//角色这一属性暂未设计
	private String state;
	private String tel;
	private String email;
	private Date birthday;
	private String memo;
	
	private List<UserRole> userRoles;
	
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public User(String id, String name, String account, String dept,
			String password, String image, boolean gender, String state,
			String tel, String email, Date birthday, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.dept = dept;
		this.password = password;
		this.image = image;
		this.gender = gender;
		this.state = state;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", account=" + account
				+ ", dept=" + dept + ", password=" + password + ", image="
				+ image + ", gender=" + gender + ", state=" + state + ", tel="
				+ tel + ", email=" + email + ", birthday=" + birthday
				+ ", memo=" + memo + "]";
	}
	
	
	
	
	
}