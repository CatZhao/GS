package cn.zhaoxiaoxue.nsfw.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.zhaoxiaoxue.nsfw.entity.User;



public interface UserService {
	//新增用户
	public void save(User user);
	//修改
	public void update(User user);
	//根据id删除
	public void delete(Serializable id);
	
	//根据id查找
	public User findById(Serializable id);
	//查找所有用户
	public List<User> findAll();
	//根据用户名模糊查找
	public List<User> findByName(String name);
	//根据账户名准确查找用户
	public boolean isAccountAvailable(String account);
	
	//使用excel导入用户
	public void importExcel(File userExcel,String userExcelFileName);
	//导出用户到excel
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
}
