package cn.zhaoxiaoxue.core.action;

import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	//����ѡ�е���
	protected String[] selectedRow;
	
	//������ݣ���json��ʽ��Ӧajax����
	protected Map<String,Object> dataMap;


	
	
	//get/set����
	@JSON(serialize=false) 
	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
}
