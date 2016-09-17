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
	 * �����û��б�xls��ʽ����֤�����ĸ�excel�汾�Ŀͻ������Դ򿪱��
	 * @param userList �û��б�
	 * @param outputStream ��������ѹ����������ķ�ʽд���������
	 */
	public static void exportUserExcel(List<User> userList,ServletOutputStream outputStream){
		try {
			//����������
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			//����������(����������ڹ��������ɹ�����������
			HSSFSheet sheet = workbook.createSheet("�û��б�");
			//�����ϲ���Ԫ�����
			CellRangeAddress headerAddress = new CellRangeAddress(0,0,0,6);
			//�ڹ���������Ӻϲ���Ԫ��
			sheet.addMergedRegion(headerAddress);
			
			//���ù�����ͨ�ø�ʽ���п�25
			sheet.setDefaultColumnWidth(20);		
			
			//������ͷ��ʽ
			HSSFCellStyle headerStyle = createCellStyle(workbook,(short)16,true);
			//������������ʽ
			HSSFCellStyle titleStyle = createCellStyle(workbook,(short)13,true);
			//��������������ʽ
			HSSFCellStyle contentStyle = createCellStyle(workbook,(short)10,false);
			
			//������ͷ��
			HSSFRow headerRow = sheet.createRow(0);
			HSSFCell headerCell = headerRow.createCell(0);
			//����ͷ������ʽ
			headerCell.setCellStyle(headerStyle);
			//���ñ�ͷ����
			headerCell.setCellValue("�û��б�");
			
			//����������
			HSSFRow titleRow = sheet.createRow(1);
			String[] titles = {"�û���","�ʺ�", "��������", "�Ա�","�ֻ�����", "��������","����"};
			for(int i =0;i<titles.length;i++){
				HSSFCell titleCell = titleRow.createCell(i);
				//�������м�����ʽ
				titleCell.setCellStyle(titleStyle);
				//���ñ���������
				titleCell.setCellValue(titles[i]);
			}	
			
			//��ȡuserList ���û���Ϣд�������
			for(int j=0;j<userList.size();j++){
				User user = userList.get(j);
				//�����û���Ϣ��
				HSSFRow userRow = sheet.createRow(2+j);
				//���û���Ϣ�������ʽ
				//userRow.setRowStyle(contentStyle);
				
				//��������
				List<HSSFCell> contentCellList = new ArrayList<HSSFCell>();						
				for(int i =0;i<titles.length;i++){
					HSSFCell contentCell = userRow.createCell(i);
					contentCell.setCellStyle(contentStyle);
					contentCellList.add(contentCell);
				}
				contentCellList.get(0).setCellValue(user.getName());
				contentCellList.get(1).setCellValue(user.getAccount());
				contentCellList.get(2).setCellValue(user.getDept());
				contentCellList.get(3).setCellValue(user.isGender()?"��":"Ů");
				contentCellList.get(4).setCellValue(user.getTel());
				contentCellList.get(5).setCellValue(user.getEmail());
				//������Ҫ������ַ�����д��excel��date��ʽд��excel����ʾ��������
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String birthday = format.format(user.getBirthday());
				contentCellList.get(6).setCellValue(birthday);
			}
			
			//�ѹ�����ͨ��outputStreamд��ȥ			
			workbook.write(outputStream);
			
			//�رչ�����
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static List<User> importUserExcel(File userExcel, String userExcelFileName){
		List<User> userList = new ArrayList<User>();;
		
		try {
			//ͨ���û��ϴ����ļ�����ȡ������
			FileInputStream inputStream = new FileInputStream(userExcel);
			
			//�ж��û��ϴ���excel�ǲ���xls����������Ӧ��workbook����
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			
			//��ȡ������(����ͨ�����ֻ�����������ȡ��
			Sheet sheet = workbook.getSheetAt(0);
			
			//�ж��ļ����������ݣ������Ƿ����2��,Ҳ�������������ж�
			int rowLength = sheet.getPhysicalNumberOfRows();
			if( rowLength>2){								
				for(int i=2;i<rowLength;i++){
					//��ȡ�ļ������ݣ���װ��user������:"�û���","�ʺ�", "��������", "�Ա�","�ֻ�����", "��������","����"
					Row row = sheet.getRow(i);
					//�ж��Ƿ����
					if(isBlankRow(row)){
						break;
					}
					User user = new User();
					
					user.setName(row.getCell(0).getStringCellValue());
					user.setAccount(row.getCell(1).getStringCellValue());
					user.setDept(row.getCell(2).getStringCellValue());
					user.setGender("��".equals(row.getCell(3).getStringCellValue()));
					//�ֻ�����excel��ȡ�Ŀ�����String��Ҳ�����ǿ�ѧ������������
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
					
					//����Ĭ������
					String password = ResourceBundle.getBundle("default").getString("password");					
					user.setPassword(password);
					//��user���浽userList��
					userList.add(user);					
				}
			}
			
			//�رչ�����
			workbook.close();
			//�ر���
			inputStream.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * ������ʽ��ˮƽ����ֱ���У��������fontsize���������С.�������isBold�����Ƿ�Ӵ֡�ͨ��������������
	 * @param workbook
	 * @param fontsize
	 * @return
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontsize,boolean isBold){
		//������Ԫ���ʽ�����������е�Ԫ��ͨ�ã������ڹ�������Ӧ�õ���Ԫ��
		HSSFCellStyle style = workbook.createCellStyle();
		//ˮƽ����
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//��ֱ����
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
				
		//�������壨�����ڹ�������
		HSSFFont font = workbook.createFont();
		
		//����Ӵ�
		if(isBold){			
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		
		//�����С
		font.setFontHeightInPoints(fontsize);
		//��������ӵ���ʽ��
		style.setFont(font);
		return style;
	}
	
	/**
	 * �ж��Ƿ��ǿ���
	 * @return 
	 */
	public static boolean isBlankRow(Row row){
		boolean result = true;
		//�ж����Ƿ�Ϊnull
		if(row != null){
			//��ȡ���е���Ч��Ԫ��
			int cellNumber = row.getPhysicalNumberOfCells();
			for(int i=0;i<cellNumber;i++){
				Cell cell = row.getCell(i);
				//�жϵ�Ԫ���Ƿ���null
				if(cell != null){
					//��ȡ��Ԫ�������
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
					//ȥ�ո�
					value = value.trim();
					//�жϵ�Ԫ�������Ƿ�Ϊ���ַ���
					//����ǿգ�˵�������˷ǿյ�Ԫ�񣬱��зǿգ���ֵtrue��result,����ѭ��
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
