package cn.zhaoxiaoxue.nsfw.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.zhaoxiaoxue.core.util.ExcelUtil;
import cn.zhaoxiaoxue.nsfw.dao.UserDao;
import cn.zhaoxiaoxue.nsfw.entity.User;
import cn.zhaoxiaoxue.nsfw.service.UserService;

public class UserServiceImpl implements UserService {
	
	//spring×¢ÈëuserDao
	private UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(User user) {
		
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);

	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);

	}

	@Override
	public User findById(Serializable id) {
		
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {		
		return userDao.findAll();
	}

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		List<User> userList = ExcelUtil.importUserExcel(userExcel, userExcelFileName);
		if(userList != null && userList.size() > 0){
			for(User user : userList){
				userDao.save(user);
			}
		}
	}

	@Override
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(userList, outputStream);
	}

	@Override
	public List<User> findByName(String name) {
		
		return userDao.findByName(name);
	}
	
	@Override
	public boolean isAccountAvailable(String account) {
		boolean result = true;
		List<User> userList = userDao.findByAccount(account);
		if(userList != null && userList.size() > 0){
			result = false;
		}
		return result;
	}

}
