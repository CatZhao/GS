package cn.zhaoxiaoxue.test.newentity;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String roleId;
	private String name;
	private String state;
	private Set userRoles = new HashSet(0);
	private Set privileges = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String name, String state) {
		this.name = name;
		this.state = state;
	}

	/** full constructor */
	public Role(String name, String state, Set userRoles, Set privileges) {
		this.name = name;
		this.state = state;
		this.userRoles = userRoles;
		this.privileges = privileges;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public Set getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(Set privileges) {
		this.privileges = privileges;
	}

}