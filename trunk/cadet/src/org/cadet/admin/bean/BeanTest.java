/**
 * 
 */
package org.cadet.admin.bean;


/**
 * @author Udit Desai
 *
 */
public class BeanTest {

	/**
	 * 
	 */
	public BeanTest() {
		// TODO Auto-generated constructor stub
	}

	private Integer testID;
	private String type;
	private String testName;
	private String testDate;
	private Integer testDuration;
	private Integer initialDifficulty;

	public Integer getTestID() {
		return testID;
	}
	public void setTestID(Integer testID) {
		this.testID = testID;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanTest [testID=" + testID + ", type=" + type + ", testName="
				+ testName + ", tDate=" + testDate + ", tDuration=" + testDuration
				+ ", initialDifficulty=" + initialDifficulty + "]";
	}
}
