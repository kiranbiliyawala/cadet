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
	private String tDate;
	private Integer tDuration;
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

	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public Integer gettDuration() {
		return tDuration;
	}
	public void settDuration(Integer tDuration) {
		this.tDuration = tDuration;
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
				+ testName + ", tDate=" + tDate + ", tDuration=" + tDuration
				+ ", initialDifficulty=" + initialDifficulty + "]";
	}
}
