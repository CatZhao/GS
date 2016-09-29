package cn.zhaoxiaoxue.core.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import cn.zhaoxiaoxue.core.service.BaseService;
import cn.zhaoxiaoxue.core.util.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	//����ѡ�е���
	protected String[] selectedRow;
	//���ղ��ҹؼ���
	protected String keyword;
	//������ݣ���json��ʽ��Ӧajax����
	protected Map<String,Object> dataMap;

	//��ҳ
	protected PageResult pr;
	protected int pageNo;
	
	//ҳ��Ĭ��ÿҳ��ʾ5��
	public static int PAGESIZE = 5;
	
	/*-----------------��ȡ��action�Ĺ�������--------------------*/

	
	/*--------get/set����--------*/
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
