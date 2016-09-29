package cn.zhaoxiaoxue.test.newentity;

/**
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */

public class UserRoleId implements java.io.Serializable {

	// Fields

	private User user;
	private Role role;

	// Constructors

	/** default constructor */
	public UserRoleId() {
	}

	/** full constructor */
	public UserRoleId(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	// Property accessors

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleId))
			return false;
		UserRoleId castOther = (UserRoleId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null
				&& castOther.getUser() != null && this.getUser().equals(
				castOther.getUser())))
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null
						&& castOther.getRole() != null && this.getRole()
						.equals(castOther.getRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		return result;
	}

}