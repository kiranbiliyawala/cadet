package org.cadet.admin.bean;

import java.sql.Date;

public class BeanTest {

	public BeanTest() {
	    // TODO Auto-generated constructor stub
	}

	private Integer testId;
	private String testType;
	private String testName;
	private String testDesc;
	private Date testDate;
	private Date startTime;
	private Date endTime;
	private Integer testDuration;
	private Integer initialDifficulty;
	private Integer negMark;

	public Integer getTestId() {
	    return testId;
	}
	public void setTestId(Integer testId) {
	    this.testId = testId;
	}

	public String getTestType() {
	    return testType;
	}
	public void setTestType(String testType) {
	    this.testType = testType;
	}

	public String getTestName() {
	    return testName;
	}
	public void setTestName(String testName) {
	    this.testName = testName;
	}

	public String getTestDesc() {
	    return testDesc;
	}
	public void setTestDesc(String testDesc) {
	    this.testDesc = testDesc;
	}

	public Date getTestDate() {
	    return testDate;
	}
	public void setTestDate(Date testDate) {
	    this.testDate = testDate;
	}

	public Date getStartTime() {
	    return startTime;
	}
	public void setStartTime(Date startTime) {
	    this.startTime = startTime;
	}

	public Date getEndTime() {
	    return endTime;
	}
	public void setEndTime(Date endTime) {
	    this.endTime = endTime;
	}

	public Integer getTestDuration() {
	    return testDuration;
	}
	public void setTestDuration(Integer testDuration) {
	    this.testDuration = testDuration;
	}

	public Integer getInitialDifficulty() {
	    return initialDifficulty;
	}
	public void setInitialDifficulty(Integer initialDifficulty) {
	    this.initialDifficulty = initialDifficulty;
	}

	public Integer getNegMark() {
	    return negMark;
	}
	public void setNegMark(Integer negMark) {
	    this.negMark = negMark;
	}

	@Override
	public String toString() {
	    return "BeanTest [testId=" + testId + ", testType=" + testType
		    + ", testName=" + testName + ", testDesc=" + testDesc
		    + ", testDate=" + testDate + ", startTime=" + startTime
		    + ", endTime=" + endTime + ", testDuration=" + testDuration
		    + ", initialDifficulty=" + initialDifficulty + "]";
	}
}
