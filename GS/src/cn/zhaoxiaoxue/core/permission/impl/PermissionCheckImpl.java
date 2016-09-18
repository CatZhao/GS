package cn.zhaoxiaoxue.core.permission.impl;

import java.util.List;
import java.util.Set;

import cn.zhaoxiaoxue.core.permission.PermissionCheck;
import cn.zhaoxiaoxue.nsfw.entity.RolePrivilege;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;
import cn.zhaoxiaoxue.nsfw.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {
	private UserService userService;	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isAccessible(User user, String module) {
		//判断用户的角色权限是否为空
		List<UserRole> userRoleList = user.getUserRoles();
		if(userRoleList == null){
			//如果空，从数据库查找一次用户权限
			userRoleList = userService.findUserRolesByUserId(user.getId());
		}
		
		if(userRoleList != null && userRoleList.size() > 0){
			//判断用户有无对应的权限
			for(UserRole userRole : userRoleList){
				Set<RolePrivilege> set = userRole.getId().getRole().getRolePrivileges();
				if(set != null && set.size() > 0){
					for(RolePrivilege rp : set){
						String userPrivilege = rp.getRolePrivilegeId().getCode();
						if(module.equals(userPrivilege)){
							return true;
						}
					}
				}				
			}
		}		
		
		//没找到对应权限，返回false
		return false;
	}
	
}
