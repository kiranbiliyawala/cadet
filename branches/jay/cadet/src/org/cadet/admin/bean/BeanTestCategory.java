package org.cadet.admin.bean;

public class BeanTestCategory {

    public BeanTestCategory() {
	// TODO Auto-generated constructor stub
    }

    private int testId;
    private int categoryId;
    private String categoryName;
    private int timePerCategory;
    private int questionsPerCategory;

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTimePerCategory() {
        return timePerCategory;
    }
    public void setTimePerCategory(int timePerCategory) {
        this.timePerCategory = timePerCategory;
    }

    public int getQuestionsPerCategory() {
        return questionsPerCategory;
    }
    public void setQuestionsPerCategory(int questionsPerCategory) {
        this.questionsPerCategory = questionsPerCategory;
    }

    public int getCategoryId() {
	return categoryId;
    }
    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

    public int getTestId() {
	return testId;
    }
    public void setTestId(int testId) {
	this.testId = testId;
    }

    @Override
    public String toString() {
	return "BeanTestCategory [testId=" + testId + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", timePerCategory=" + timePerCategory + ", questionsPerCategory="
		+ questionsPerCategory + "]";
    }
}
