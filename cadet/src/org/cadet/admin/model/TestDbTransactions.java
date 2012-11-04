/**
 * 
 */
package org.cadet.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.cadet.admin.bean.BeanCategory;
import org.cadet.admin.bean.BeanTest;
import org.cadet.admin.bean.BeanTestCategory;
import org.cadet.util.model.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Udit Desai
 * 
 */
public class TestDbTransactions {

    /**
	 * 
	 */
    public TestDbTransactions() {
	// TODO Auto-generated constructor stub

    }

    public static JSONObject getAllTests(Connection connection) throws SQLException, JSONException {

	Statement statement = connection.createStatement();

	ArrayList<BeanTest> result = new ArrayList<BeanTest>();
	BeanTest temp = null;

	ResultSet rs = statement.executeQuery(Constants.sqlCommands.retriveTests);
	while (rs.next()) {

	    temp = new BeanTest();
	    temp.setTestId(rs.getInt("TestId"));
	    temp.setTestType(rs.getString("TestType"));
	    temp.setTestName(rs.getString("TestName"));
	    temp.setTestDesc(rs.getString("TestDesc"));
	    temp.setTestDate(rs.getDate("TestDate"));
	    temp.setStartTime(rs.getTimestamp("StartTime"));
	    temp.setEndTime(rs.getTimestamp("EndTime"));
	    temp.setTestDuration(rs.getInt("TestDuration"));
	    temp.setInitialDifficulty(rs.getInt("InitialDifficulty"));

	    result.add(temp);
	}
	rs.close();
	statement.close();

	JSONObject data = new JSONObject();
	data.put("testList", result.toArray(new BeanTest[result.size()]));

	return data;
    }

    public static JSONObject getAllCategories(Connection connection) {

	Statement statement = connection.createStatement();
	ArrayList<BeanCategory> result = new ArrayList<BeanCategory>();
	BeanCategory temp = null;

	ResultSet rs = statement.executeQuery(Constants.sqlCommands.retriveCategories);

	JSONObject data = new JSONObject();
	data.put("categoryList",);
    }

    public static void createTest(Connection connection, String testName, String testType, String testDesc) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.AddTest);
	ps.setString(1, testName);
	ps.setString(2, testType);
	ps.setString(3, testDesc);

	ps.executeUpdate();
	ps.close();
    }

    public static int getLastInsertID(Connection connection) throws SQLException {

	Statement statement = connection.createStatement();

	ResultSet rs = statement.executeQuery(Constants.sqlCommands.retriveLastInsertID);
	if (rs.next())
	    return rs.getInt(1);

	return -1;
    }

    public static void deleteTest(Connection connection, int testId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.deleteTest);
	ps.setInt(1, testId);

	ps.executeUpdate();
	ps.close();
    }

    public static ArrayList<BeanTestCategory> getTestCategoryDetails(Connection connection, int testId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveTestCategoryDetails);
	ps.setInt(1, testId);

	ArrayList<BeanTestCategory> result = new ArrayList<BeanTestCategory>();
	BeanTestCategory temp = null;

	ResultSet rs = ps.executeQuery();
	while (rs.next()) {

	    temp = new BeanTestCategory();
	    temp.setCategoryId(rs.getInt("CategoryId"));
	    temp.setCategoryName(rs.getString("CategoryName"));
	    temp.setQuestionsPerCategory(rs.getInt("QuestionPerCategory"));
	    temp.setTimePerCategory(rs.getInt("TimePerCategory"));
	    result.add(temp);
	}

	rs.close();
	ps.close();

	return result;
    }

    public static void setTestCategoryDetails(Connection connection,int testId,int categoryId,int questionsPerCategory,int timePerCategory) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.UpdateTestCategoryDetails);
	ps.setInt(1, timePerCategory);
	ps.setInt(2, questionsPerCategory);
	ps.setInt(3, testId);
	ps.setInt(4, categoryId);

	ps.executeUpdate();
	ps.close();
    }

    public static BeanTest getTestDetails(Connection connection, int testId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveTestDetails);
	ps.setInt(1, testId);

	BeanTest result = null;

	ResultSet rs = ps.executeQuery();
	if(rs.next()) {

	    result = new BeanTest();
	    result.setTestId(testId);
	    result.setTestType(rs.getString("TestType")); 
	    result.setTestName(rs.getString("TestName"));
	    result.setTestDesc(rs.getString("TestDesc"));
	    result.setTestDate(rs.getDate("TestDate"));
	    result.setStartTime(rs.getTimestamp("StartTime"));
	    result.setEndTime(rs.getTimestamp("EndTime"));
	    result.setTestDuration(rs.getInt("TestDuration"));
	    result.setInitialDifficulty(rs.getInt("InitialDifficulty"));
	}
	
	rs.close();
	ps.close();

	return result;
    }
}