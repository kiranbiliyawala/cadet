package org.cadet.admin.bean;

public class BeanCategory {

    public BeanCategory() {
	// TODO Auto-generated constructor stub
    }

    private int categoryId;
    private String categoryName;
    private String categoryDesc;

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
   
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @Override
    public String toString() {
	return "BeanCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDesc=" + categoryDesc + "]";
    }
}
