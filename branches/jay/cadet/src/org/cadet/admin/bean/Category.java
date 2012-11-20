package org.cadet.admin.bean;

/**
 * @author KIRAN
 *
 */

public class Category {
	private String CategoryId;
	private String categoryName;
	private String categoryDescription;
	private String adminUsername;

	public String getCategoryId(){
		return CategoryId;
	}
	
	public void setCategoryId(String categoryId){
		this.CategoryId = categoryId;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName; 
	}
	
	public String getCategoryDescription(){
		return categoryDescription;
	}
	
	public void setCategoryDescription(String categoryDescription){
		this.categoryDescription = categoryDescription;
	}
	
	public String getAdminUsername(){
		return adminUsername;
	}
	
	public void setAdminUsername(String adminUsername){
		this.adminUsername = adminUsername;
	}

}
