package cn.zhaoxiaoxue.nsfw.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	private RolePrivilegeId rolePrivilegeId;

	public RolePrivilege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolePrivilege(RolePrivilegeId rolePrivilegeId) {
		super();
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public RolePrivilegeId getRolePrivilegeId() {
		return rolePrivilegeId;
	}

	public void setRolePrivilegeId(RolePrivilegeId rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}
	
}
