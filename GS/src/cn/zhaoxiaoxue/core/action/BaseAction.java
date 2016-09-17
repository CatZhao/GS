package cn.zhaoxiaoxue.core.action;

import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	//接收选中的行
	protected String[] selectedRow;
	
	//存放数据，以json格式响应ajax请求
	protected Map<String,Object> dataMap;


	
	
	//get/set方法
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
