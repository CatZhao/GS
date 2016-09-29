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
	
	//springע��userService
	private UserService userService;	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	//struts����Զ��ѱ����ݷ���valueStack(������get set������
	private List<User> userList;
	private User user;
	//�ļ��ϴ�����������ļ����ļ������ļ����͵�����
	private File image;
	private String imageFileName;
	private String imageContentType;
	

	//�����û������excel���
	private File userExcel;
	private String userExcelFileName;
	
	//��ɫ�б�
	private List<Role> roleList;	
	//�û���Ӧ�Ľ�ɫid
	private String[] roleIds;
	
	
	
	//���û��б�����excel�У�
	public void exportExcel(){
		HttpServletResponse response;
		ServletOutputStream outputStream;
		
		try {
			response = ServletActionContext.getResponse();			
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("�û��б�.xls".getBytes(), "ISO-8859-1"));
		
			outputStream = response.getOutputStream();
			userList = userService.findAll();
			userService.exportExcel(userList, outputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//���û��ϴ���excel�ļ��е����ݵ��뵽���ݿ�
	public String importExcel(){
		if(userExcel != null && userExcelFileName != null){
			//�ж��û��ϴ��ļ��Ƿ���excel�ļ�
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){				
				userService.importExcel(userExcel, userExcelFileName);
			}
		}
		return "list";
	}

	//����idɾ���û�
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	
	//ɾ��ѡ�е��û�
	public String deleteSelected(){
		if(selectedRow != null && selectedRow.length > 0)
		for(String id :selectedRow){
			userService.delete(id);
		}
		return "list";
	}
	
	//��ҳչʾ�������ݣ�ȥlistUIҳ��
	public String listUI(){
		Map<String,Object> condition = new HashMap<String,Object>();
		if(keyword != null){
			condition.put(" name like ? ", "%"+keyword+"%");
		}
		pr = userService.getPageResult(pageNo, PAGESIZE,condition);
		return "listUI";
	}
	
	//������Ӱ�ť���������������תȥaddUI.jsp
	public String addUI(){
		roleList = roleService.findAll();
		return "addUI";
	}
	
	//��addUI.jspҳ�����ύ�����ô˷��������յ���listUI����
	public String add(){
		try {
			if(user != null){
				if(image != null){
					String imgPath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//uuid����ͷ��Ψһ����
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
	
	//ת��ȥeditUI.jspҳ�棬���ݻ���
	public String editUI(){
		roleList = roleService.findAll();
		if(user != null && user.getId() != null){
			user = userService.findById(user.getId());
			
			//��ȡ�û���Ӧ�Ľ�ɫ
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
	
	//�ύ�޸ĺ����ϵ�����ݣ����浽���ݿ⣬��תȥlistUI.jsp
	public String edit(){
		try {
			if(user != null){
				if(image != null){
					String imgPath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//uuid����ͷ��Ψһ����
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

	//����ajax������user.account���ݣ���֤�˻����Ƿ���ã����÷���true�������÷���false
	public String checkAccount(){
		try {
			dataMap = new HashMap<String,Object>();
			//�����˻����������ݿ�
			if(user != null && user.getAccount() != null){
				boolean msg = userService.isAccountAvailable(user);	
				dataMap.put("msg", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	//get/set����
	
	
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
