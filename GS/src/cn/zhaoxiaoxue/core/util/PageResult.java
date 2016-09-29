package cn.zhaoxiaoxue.core.util;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	//�ܼ�¼��
	private long totalCount;
	//��ǰҳ��
	private int pageNo;
	//ÿҳ����
	private int pageSize;
	//��ҳ��
	private int totalPage;
	//��ǰҳ����
	private List items;	
	
	/*------------���췽��------------*/
	public PageResult(long totalCount, int pageNo, int pageSize,List items) {
		super();
		this.totalCount = totalCount;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		//ȷ����ɳ�ʼ����ֹ���ڳ���NullPoint
		this.items = (items == null) ? (new ArrayList()) : items;
		//������ҳ��
		if(pageSize != 0){
			int tem = (int) (totalCount/pageSize);
			totalPage = (totalCount % pageSize == 0) ? tem : (tem+1);
		}
	}
	
	/*------------get/set����------------*/
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
