package cn.zhaoxiaoxue.nsfw.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.zhaoxiaoxue.core.action.BaseAction;
import cn.zhaoxiaoxue.core.constant.Constant;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.entity.RolePrivilege;
import cn.zhaoxiaoxue.nsfw.entity.RolePrivilegeId;
import cn.zhaoxiaoxue.nsfw.service.RoleService;

public class RoleAction extends BaseAction {
	private RoleService roleService;

	private Role role;
	private List<Role> roleList;

	private Map<String,String> privilegeMap = Constant.PRIVILEGE_MAP;
	private String[] privilegeIds;
	
	public String searchUI() {
		if (role != null && role.getName() != null) {
			roleList = roleService.findByName(role.getName().trim());
		}
		return "searchUI";
	}
	
	public String listUI(){
		roleList = roleService.findAll();
		return "listUI";
	}
	
	public String addUI(){
		return "addUI";
	}
	
	public String add(){
		if(role != null){
			roleService.save(role);
		}
		return "list";
	}
	
	
	public String editUI(){
		if(role != null && role.getRoleId() != null){
			role = roleService.findById(role.getRoleId());
			//处理权限回显问题
			Set<RolePrivilege> rpSet = role.getRolePrivileges();
			int rpNum = rpSet.size();
			if( rpSet != null && rpNum > 0){
				privilegeIds = new String[rpNum];
				int i = 0;
				for(RolePrivilege rp : rpSet){
					privilegeIds[i++] = rp.getRolePrivilegeId().getCode();
				}
			}
		}
		return "editUI";
	}
	public String edit(){
		if(role != null && role.getRoleId() != null){			
			roleService.update(role);
		}
		return "list";
	}
	
	public String delete(){
		if(role != null && role.getRoleId() != null){
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	public String deleteSelected(){
		if(selectedRow != null &&selectedRow.length > 0){
			for(String roleId : selectedRow){
				roleService.delete(roleId);
			}
		}
		return "list";
	}
	
	
	// get/set方法
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		if(role != null){
			if(privilegeIds != null && privilegeIds.length > 0){
				Set<RolePrivilege> set = new HashSet<RolePrivilege>();
				
				for(String id : privilegeIds){					
					set.add(new RolePrivilege(new RolePrivilegeId(role,id)));
				}
				
				role.setRolePrivileges(set);
			}
		}
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Map<String, String> getPrivilegeMap() {
		return privilegeMap;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
