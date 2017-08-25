package domain;

import java.io.Serializable;
import java.util.List;

public class productType implements Serializable{
	private int id;
	private String name;
	private int parentId;
	private String sort;
	private String intro;
	private String create_date;
	
	private List<productType> childBeans;
	
	
	public List<productType> getChildBeans() {
		return childBeans;
	}
	public void setChildBeans(List<productType> childBeans) {
		this.childBeans = childBeans;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public productType() {
		
	}
	public productType(int id, String name, int parentId, String sort, String intro, String create_date) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.intro = intro;
		this.create_date = create_date;
	}
	
}
