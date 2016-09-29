package cn.zhaoxiaoxue.test.newentity;

/**
 * PrivilegeId entity. @author MyEclipse Persistence Tools
 */

public class PrivilegeId implements java.io.Serializable {

	// Fields

	private Role role;
	private String code;

	// Constructors

	/** default constructor */
	public PrivilegeId() {
	}

	/** full constructor */
	public PrivilegeId(Role role, String code) {
		this.role = role;
		this.code = code;
	}

	// Property accessors

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrivilegeId))
			return false;
		PrivilegeId castOther = (PrivilegeId) other;

		return ((this.getRole() == castOther.getRole()) || (this.getRole() != null
				&& castOther.getRole() != null && this.getRole().equals(
				castOther.getRole())))
				&& ((this.getCode() == castOther.getCode()) || (this.getCode() != null
						&& castOther.getCode() != null && this.getCode()
						.equals(castOther.getCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		return result;
	}

}