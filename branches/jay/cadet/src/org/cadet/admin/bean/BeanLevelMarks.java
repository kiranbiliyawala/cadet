package org.cadet.admin.bean;

public class BeanLevelMarks {

    public BeanLevelMarks() {
	// TODO Auto-generated constructor stub
    }

    private int levelId;
    private int testId;
    private int marks;

    public int getLevelId() {
        return levelId;
    }
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getTestId() {
        return testId;
    }
    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
	return "BeanLevelMarks [levelId=" + levelId + ", testId=" + testId + ", marks=" + marks + "]";
    }
}
