package org.cadet.client.bean;
/**
 * 
 */

import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author Shailee
 *
 *
 */
public class BeanTest {

	/**
	 * 
	 */
	public BeanTest() {
		// TODO Auto-generated constructor stub
	}

	private Integer testId;
	private String testType;
	private String testName;
	private String testDesc;
	private Date testDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer testDuration;
	private Integer initialDifficulty;

	/**
	 * @return the testId
	 */
	public Integer getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	/**
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}
	/**
	 * @param testType the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}
	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	/**
	 * @return the testDesc
	 */
	public String getTestDesc() {
		return testDesc;
	}
	/**
	 * @param testDesc the testDesc to set
	 */
	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}
	/**
	 * @return the testDate
	 */
	public Date getTestDate() {
		return testDate;
	}
	/**
	 * @param testDate the testDate to set
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the testDuration
	 */
	public Integer getTestDuration() {
		return testDuration;
	}
	/**
	 * @param testDuration the testDuration to set
	 */
	public void setTestDuration(Integer testDuration) {
		this.testDuration = testDuration;
	}
	/**
	 * @return the initialDifficulty
	 */
	public Integer getInitialDifficulty() {
		return initialDifficulty;
	}
	/**
	 * @param initialDifficulty the initialDifficulty to set
	 */
	public void setInitialDifficulty(Integer initialDifficulty) {
		this.initialDifficulty = initialDifficulty;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanTest [testId=" + testId + ", testType=" + testType
				+ ", testName=" + testName + ", testDesc=" + testDesc
				+ ", testDate=" + testDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", testDuration=" + testDuration
				+ ", initialDifficulty=" + initialDifficulty + "]";
	}
}
