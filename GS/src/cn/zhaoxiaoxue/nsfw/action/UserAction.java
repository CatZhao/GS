package cn.zhaoxiaoxue.nsfw.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import cn.zhaoxiaoxue.core.action.BaseAction;
import cn.zhaoxiaoxue.core.constant.Constant;
import cn.zhaoxiaoxue.core.util.ExcelUtil;
import cn.zhaoxiaoxue.core.util.PageResult;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.entity.UserRole;
import cn.zhaoxiaoxue.nsfw.service.RoleService;
import cn.zhaoxiaoxue.nsfw.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {
	
	//spring注入userService
	private UserService userService;	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	//struts框架自动把表单数据放入valueStack(必须有get set方法）
	private List<User> userList;
	private User user;
	//文件上传组件传来的文件，文件名，文件类型等数据
	private File image;
	private String imageFileName;
	private String imageContentType;
	

	//接收用户导入的excel表格
	private File userExcel;
	private String userExcelFileName;
	
	//角色列表
	private List<Role> roleList;	
	//用户对应的角色id
	private String[] roleIds;
	
	
	
	//把用户列表导出到excel中，
	public void exportExcel(){
		HttpServletResponse response;
		ServletOutputStream outputStream;
		
		try {
			response = ServletActionContext.getResponse();			
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
		
			outputStream = response.getOutputStream();
			userList = userService.findAll();
			userService.exportExcel(userList, outputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//把用户上传的excel文件中的内容导入到数据库
	public String importExcel(){
		if(userExcel != null && userExcelFileName != null){
			//判断用户上传文件是否是excel文件
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){				
				userService.importExcel(userExcel, userExcelFileName);
			}
		}
		return "list";
	}

	//根据id删除用户
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	
	//删除选中的用户
	public String deleteSelected(){
		if(selectedRow != null && selectedRow.length > 0)
		for(String id :selectedRow){
			userService.delete(id);
		}
		return "list";
	}
	
	//分页展示所有数据，去listUI页面
	public String listUI(){
		Map<String,Object> condition = new HashMap<String,Object>();
		if(keyword != null){
			condition.put(" name like ? ", "%"+keyword+"%");
		}
		pr = userService.getPageResult(pageNo, PAGESIZE,condition);
		return "listUI";
	}
	
	//点击增加按钮调用这个方法，跳转去addUI.jsp
	public String addUI(){
		roleList = roleService.findAll();
		return "addUI";
	}
	
	//在addUI.jsp页面点击提交，调用此方法，最终调用listUI方法
	public String add(){
		try {
			if(user != null){
				if(image != null){
					String imgPath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//uuid生成头像唯一名称
					String imgName = UUID.randomUUID().toString().replace("-", "")
							+imageFileName.substring(imageFileName.lastIndexOf("."));
					FileUtils.copyFile(image, new File(imgPath,imgName));
					user.setImage("user/"+imgName);
				}
				userService.saveUserAndRole(user, roleIds);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	
	//转发去editUI.jsp页面，数据回显
	public String editUI(){
		roleList = roleService.findAll();
		if(user != null && user.getId() != null){
			user = userService.findById(user.getId());
			
			//获取用户对应的角色
			List<UserRole> userRoleList = userService.findUserRolesByUserId(user.getId());
			int roleNum = userRoleList.size();
			roleIds = new String[roleNum];
			
			if(userRoleList != null && roleNum > 0){
				int i = 0;
				for(UserRole userRole : userRoleList){
					roleIds[i++] = userRole.getId().getRole().getRoleId();
				}
			}
		}
		return "editUI";
	}
	
	//提交修改后的联系人数据，保存到数据库，跳转去listUI.jsp
	public String edit(){
		try {
			if(user != null){
				if(image != null){
					String imgPath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//uuid生成头像唯一名称
					String imgName = UUID.randomUUID().toString().replace("-", "")
							+ imageFileName.substring(imageFileName.lastIndexOf("."));
					FileUtils.copyFile(image, new File(imgPath,imgName));
					user.setImage("user/"+imgName);
				}
				userService.updateUserAndRole(user, roleIds);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}

	//接收ajax传来的user.account数据，验证账户名是否可用，可用返回true，不可用返回false
	public String checkAccount(){
		try {
			dataMap = new HashMap<String,Object>();
			//根据账户名查找数据库
			if(user != null && user.getAccount() != null){
				boolean msg = userService.isAccountAvailable(user);	
				dataMap.put("msg", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	//get/set方法
	
	
	@JSON(serialize=false) 
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@JSON(serialize=false) 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize=false) 
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@JSON(serialize=false) 
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	@JSON(serialize=false) 
	public File getUserExcel() {
		return userExcel;
	}
	
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	
	@JSON(serialize=false) 
	public String getUserExcelFileName() {
		return userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

	@JSON(serialize=false)
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	@JSON(serialize=false)
	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	@JSON(serialize=false)
	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	


}
