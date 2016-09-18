package cn.zhaoxiaoxue.nsfw.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
	private UserRoleId id;

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public UserRole(UserRoleId id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + "]";
	}
	
}
