package cn.zhaoxiaoxue.nsfw.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.zhaoxiaoxue.nsfw.entity.User;



public interface UserService {
	//�����û�
	public void save(User user);
	//�޸�
	public void update(User user);
	//����idɾ��
	public void delete(Serializable id);
	
	//����id����
	public User findById(Serializable id);
	//���������û�
	public List<User> findAll();
	//�����û���ģ������
	public List<User> findByName(String name);
	//�����˻���׼ȷ�����û�
	public boolean isAccountAvailable(String account);
	
	//ʹ��excel�����û�
	public void importExcel(File userExcel,String userExcelFileName);
	//�����û���excel
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
}
