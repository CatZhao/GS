package cn.zhaoxiaoxue.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import cn.zhaoxiaoxue.nsfw.entity.User;

public class ExcelUtilTest {
	@Test
	public void test_POI(){
		List<User> userList = ExcelUtil.importUserExcel(
				new File("C:/Users/zhaoxiaoxue/Desktop/用户列表.xls"), "用户列表.xls");
		for(User user:userList){
			
			System.out.println(user);
		}
	}
	@Test
	public void test_resourceBundler(){
		String password = ResourceBundle.getBundle("default").getString("password");
		System.out.println(password);
	}
	
	@Test
	public void test_break(){
		for(int i=0;i<5;i++){
			/*if(i==1){
				break;
				//打破外层for循环，运行结果是：0*************content******   break for
			}*/
			switch(i){
			case 0:
				break;
			case 1:
				System.out.println(i+"----switch-----");
			default:break;				
			}
			System.out.println(i+"*************content******");			
		}
		System.out.println("break for");
	}
}
