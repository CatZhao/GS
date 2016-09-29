package cn.zhaoxiaoxue.core.util;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	//总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//每页条数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数据
	private List items;	
	
	/*------------构造方法------------*/
	public PageResult(long totalCount, int pageNo, int pageSize,List items) {
		super();
		this.totalCount = totalCount;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		//确保完成初始化防止后期出现NullPoint
		this.items = (items == null) ? (new ArrayList()) : items;
		//计算总页数
		if(pageSize != 0){
			int tem = (int) (totalCount/pageSize);
			totalPage = (totalCount % pageSize == 0) ? tem : (tem+1);
		}
	}
	
	/*------------get/set方法------------*/
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
}
