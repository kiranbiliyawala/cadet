package org.cadet.client.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class DashboardBean {
	private Integer testId;
	private String testType;
	private String testName;
	private Date testDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer testDuration;
	
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
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(Integer testDuration) {
		this.testDuration = testDuration;
	}
	

}
