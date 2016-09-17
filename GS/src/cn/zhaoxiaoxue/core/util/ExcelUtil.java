package cn.zhaoxiaoxue.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.zhaoxiaoxue.nsfw.entity.User;

public class ExcelUtil {
	
	/**
	 * 导出用户列表（xls格式，保证无论哪个excel版本的客户都可以打开表格）
	 * @param userList 用户列表
	 * @param outputStream 输出流，把工作簿用流的方式写出到浏览器
	 */
	public static void exportUserExcel(List<User> userList,ServletOutputStream outputStream){
		try {
			//创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			//创建工作表(工作表从属于工作簿，由工作簿创建）
			HSSFSheet sheet = workbook.createSheet("用户列表");
			//创建合并单元格对象
			CellRangeAddress headerAddress = new CellRangeAddress(0,0,0,6);
			//在工作表中添加合并单元格
			sheet.addMergedRegion(headerAddress);
			
			//设置工作表通用格式：列宽25
			sheet.setDefaultColumnWidth(20);		
			
			//创建表头样式
			HSSFCellStyle headerStyle = createCellStyle(workbook,(short)16,true);
			//创建标题行样式
			HSSFCellStyle titleStyle = createCellStyle(workbook,(short)13,true);
			//创建内容区域样式
			HSSFCellStyle contentStyle = createCellStyle(workbook,(short)10,false);
			
			//创建表头行
			HSSFRow headerRow = sheet.createRow(0);
			HSSFCell headerCell = headerRow.createCell(0);
			//给表头加上样式
			headerCell.setCellStyle(headerStyle);
			//设置表头内容
			headerCell.setCellValue("用户列表");
			
			//创建标题行
			HSSFRow titleRow = sheet.createRow(1);
			String[] titles = {"用户名","帐号", "所属部门", "性别","手机号码", "电子邮箱","生日"};
			for(int i =0;i<titles.length;i++){
				HSSFCell titleCell = titleRow.createCell(i);
				//给标题行加上样式
				titleCell.setCellStyle(titleStyle);
				//设置标题行内容
				titleCell.setCellValue(titles[i]);
			}	
			
			//读取userList 把用户信息写道表格中
			for(int j=0;j<userList.size();j++){
				User user = userList.get(j);
				//创建用户信息行
				HSSFRow userRow = sheet.createRow(2+j);
				//给用户信息行添加样式
				//userRow.setRowStyle(contentStyle);
				
				//设置内容
				List<HSSFCell> contentCellList = new ArrayList<HSSFCell>();						
				for(int i =0;i<titles.length;i++){
					HSSFCell contentCell = userRow.createCell(i);
					contentCell.setCellStyle(contentStyle);
					contentCellList.add(contentCell);
				}
				contentCellList.get(0).setCellValue(user.getName());
				contentCellList.get(1).setCellValue(user.getAccount());
				contentCellList.get(2).setCellValue(user.getDept());
				contentCellList.get(3).setCellValue(user.isGender()?"男":"女");
				contentCellList.get(4).setCellValue(user.getTel());
				contentCellList.get(5).setCellValue(user.getEmail());
				//生日需要处理成字符串再写入excel，date格式写入excel，显示的是数字
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String birthday = format.format(user.getBirthday());
				contentCellList.get(6).setCellValue(birthday);
			}
			
			//把工作簿通过outputStream写出去			
			workbook.write(outputStream);
			
			//关闭工作簿
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static List<User> importUserExcel(File userExcel, String userExcelFileName){
		List<User> userList = new ArrayList<User>();;
		
		try {
			//通过用户上传的文件，获取输入流
			FileInputStream inputStream = new FileInputStream(userExcel);
			
			//判断用户上传的excel是不是xls，并创建对应的workbook对象
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			
			//获取工作表(可以通过名字或者索引来获取）
			Sheet sheet = workbook.getSheetAt(0);
			
			//判断文件中有无内容（行数是否大于2）,也可以用行数来判断
			int rowLength = sheet.getPhysicalNumberOfRows();
			if( rowLength>2){								
				for(int i=2;i<rowLength;i++){
					//获取文件中内容，封装到user对象中:"用户名","帐号", "所属部门", "性别","手机号码", "电子邮箱","生日"
					Row row = sheet.getRow(i);
					//判断是否空行
					if(isBlankRow(row)){
						break;
					}
					User user = new User();
					
					user.setName(row.getCell(0).getStringCellValue());
					user.setAccount(row.getCell(1).getStringCellValue());
					user.setDept(row.getCell(2).getStringCellValue());
					user.setGender("男".equals(row.getCell(3).getStringCellValue()));
					//手机号码excel获取的可能是String，也可能是科学记数法的数字
					String tel = "";					
					try {
						tel = row.getCell(4).getStringCellValue();
					} catch (Exception e) {
						double dMobile = row.getCell(4).getNumericCellValue();
						tel = BigDecimal.valueOf(dMobile).toPlainString();
					}
					user.setTel(tel);
					user.setEmail(row.getCell(5).getStringCellValue());
					user.setBirthday(row.getCell(6).getDateCellValue());
					
					//设置默认密码
					String password = ResourceBundle.getBundle("default").getString("password");					
					user.setPassword(password);
					//吧user保存到userList中
					userList.add(user);					
				}
			}
			
			//关闭工作簿
			workbook.close();
			//关闭流
			inputStream.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * 返回样式：水平，垂直居中，传入参数fontsize设置字体大小.传入参数isBold设置是否加粗。通常用于内容区域
	 * @param workbook
	 * @param fontsize
	 * @return
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontsize,boolean isBold){
		//创建单元格格式（工作簿所有单元格通用，从属于工作簿，应用到单元格）
		HSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//垂直居中
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
				
		//创建字体（从属于工作簿）
		HSSFFont font = workbook.createFont();
		
		//字体加粗
		if(isBold){			
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		
		//字体大小
		font.setFontHeightInPoints(fontsize);
		//把字体添加到格式中
		style.setFont(font);
		return style;
	}
	
	/**
	 * 判断是否是空行
	 * @return 
	 */
	public static boolean isBlankRow(Row row){
		boolean result = true;
		//判断行是否为null
		if(row != null){
			//获取行中的有效单元格
			int cellNumber = row.getPhysicalNumberOfCells();
			for(int i=0;i<cellNumber;i++){
				Cell cell = row.getCell(i);
				//判断单元格是否是null
				if(cell != null){
					//获取单元格的内容
					String value = "";
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						value = String.valueOf(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;	
					}
					//去空格
					value = value.trim();
					//判断单元格内容是否为空字符串
					//如果非空，说明出现了非空单元格，本行非空，则赋值true给result,结束循环
					if((!"".equals(value)) ){
						result = false;
						break;
					}					
				}
			}
		}
		
		return result;
	}
}
