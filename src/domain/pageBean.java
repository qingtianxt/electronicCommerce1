package domain;

import java.util.List;

public class pageBean<T> {
	
	private List<T> list;
	private int totalCount;//总数量
	private int totalPage;//总页数
	private int currPage;//当前页
	private int pageSize;//每页多少数据
	
	private String prefixUrl;
	private boolean isAnd;//true的时候是&否则是？
	
	public List<T> getList() {
		return list;
	}
	public String getPrefixUrl() {
		return prefixUrl;
	}
	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}
	public boolean isAnd() {
		return isAnd;
	}
	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public pageBean() {
		
	}
	public pageBean(List<T> list, int totalCount, int currPage, int pageSize) {
		super();
		this.list = list;
		this.totalCount = totalCount;
		this.currPage = currPage;
		this.pageSize = pageSize;
	}
	
}
