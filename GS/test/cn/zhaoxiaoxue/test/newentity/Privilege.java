package cn.zhaoxiaoxue.test.newentity;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private PrivilegeId id;

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(PrivilegeId id) {
		this.id = id;
	}

	// Property accessors

	public PrivilegeId getId() {
		return this.id;
	}

	public void setId(PrivilegeId id) {
		this.id = id;
	}

}