package cn.zhaoxiaoxue.core.permission;

import cn.zhaoxiaoxue.nsfw.entity.User;

public interface PermissionCheck {
	//�ж��û�����Ȩ�޷��ʵ�ǰ���
	public boolean isAccessible(User user,String module);
}
