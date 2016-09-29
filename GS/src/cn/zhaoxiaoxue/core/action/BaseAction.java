package cn.zhaoxiaoxue.core.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.core.util.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	//接收选中的行
	protected String[] selectedRow;
	//接收查找关键字
	protected String keyword;
	//存放数据，以json格式响应ajax请求
	protected Map<String,Object> dataMap;

	//分页
	protected PageResult pr;
	protected int pageNo;
	
	//页面默认每页显示5行
	public static int PAGESIZE = 5;
	
	/*-----------------抽取的action的公共方法--------------------*/

	
	/*--------get/set方法--------*/
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
	
	@JSON(serialize=false) 
	public PageResult getPr() {
		return pr;
	}
	public void setPr(PageResult pr) {
		this.pr = pr;
	}
	@JSON(serialize=false) 
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	@JSON(serialize=false) 
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	
	
}
