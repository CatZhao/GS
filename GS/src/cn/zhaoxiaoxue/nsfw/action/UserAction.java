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
import cn.zhaoxiaoxue.core.util.ExcelUtil;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {
	
	//springע��userService
	private UserService userService;	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	//struts����Զ��ѱ����ݷ���valueStack(������get set������
	private List<User> userList;
	private User user;
	//�ļ��ϴ�����������ļ����ļ������ļ����͵�����
	private File image;
	private String imageFileName;
	//private String headImgContentType;
	

	//�����û������excel���
	private File userExcel;
	private String userExcelFileName;
	
	
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
	
	//�����û�������
	public String searchUI(){
		if(user != null && user.getName() != null)
		userList = userService.findByName(user.getName());
		return "searchUI";
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
	//չʾ�����û��б�ȥlistUIҳ��
	public String listUI(){
		userList = userService.findAll();
		return "listUI";
	}
	
	//������Ӱ�ť���������������תȥaddUI.jsp
	public String addUI(){		
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
				userService.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	
	//ת��ȥeditUI.jspҳ�棬���ݻ���
	public String editUI(){
		if(user != null && user.getId() != null){
			user = userService.findById(user.getId());
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
				userService.update(user);
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
				boolean msg = userService.isAccountAvailable(user.getAccount());	
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




}
