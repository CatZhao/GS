package cn.zhaoxiaoxue.core.permission;

import cn.zhaoxiaoxue.nsfw.entity.User;

public interface PermissionCheck {
	//判断用户有无权限访问当前板块
	public boolean isAccessible(User user,String module);
}
