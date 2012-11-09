/**
 * 
 */
package org.cadet.admin.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.cadet.admin.bean.BeanCategory;
import org.cadet.admin.bean.BeanLevelMarks;
import org.cadet.admin.bean.BeanTest;
import org.cadet.admin.bean.BeanTestCategory;
import org.cadet.admin.bean.BeanUserCategory;
import org.cadet.client.bean.Question;
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
	    temp.setStartTime(rs.getDate("StartTime"));
	    temp.setEndTime(rs.getDate("EndTime"));
	    temp.setTestDuration(rs.getInt("TestDuration"));
	    temp.setInitialDifficulty(rs.getInt("InitialDifficulty"));
	    temp.setNegMark(rs.getInt("NegMark"));

	    result.add(temp);
	}
	rs.close();
	statement.close();

	JSONObject data = new JSONObject();
	data.put("testList", result.toArray(new BeanTest[result.size()]));

	return data;
    }

    public static JSONObject getAllCategories(Connection connection,int testId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveCategories);
	ps.setInt(1, testId);

	ArrayList<BeanCategory> result = new ArrayList<BeanCategory>();
	BeanCategory temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new BeanCategory();
	    temp.setCategoryId(rs.getInt("CategoryId"));
	    temp.setCategoryName(rs.getString("CategoryName"));
	    temp.setCategoryDesc(rs.getString("CategoryDescription"));

	    result.add(temp);
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();
	data.put("categoryList", result.toArray(new BeanCategory[result.size()]));

	return data;
    }

    public static void createTest(Connection connection, String testName, String testType, String testDesc) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addTest);
	ps.setString(1, testName);
	ps.setString(2, testType);
	ps.setString(3, testDesc);
	ps.setString(4, new Date(new java.util.Date().getTime()).toString());
	ps.setString(5, new Date(new java.util.Date().getTime()).toString()+" 00:00");
	ps.setString(6, new Date(new java.util.Date().getTime()).toString()+" 00:00");
	ps.setInt(7, 0);
	ps.setInt(8, 0);
	ps.setInt(9, 0);

	ps.executeUpdate();
	ps.close();

	ps = connection.prepareStatement(Constants.sqlCommands.addLevelMarks);

	for(int i=1;i<=10;i++) {
	    ps.setInt(1, i);
	    ps.setInt(2, getLastInsertID(connection));
	    ps.setInt(3, i);

	    ps.executeUpdate();
	}
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
	    temp.setTestId(testId);
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

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateTestCategoryDetails);
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
	    result.setStartTime(new Date(rs.getTimestamp("StartTime").getTime()));
	    result.setEndTime(new Date(rs.getTimestamp("EndTime").getTime()));
	    result.setTestDuration(rs.getInt("TestDuration"));
	    result.setInitialDifficulty(rs.getInt("InitialDifficulty"));
	    result.setNegMark(rs.getInt("NegMark"));
	}

	rs.close();
	ps.close();

	return result;
    }

    public static void addCategory(Connection connection,String categoryName) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addCategory);
	ps.setString(1, categoryName);
	ps.setString(2, "NA");

	ps.executeUpdate();
	ps.close();
    }

    public static void addCategoryToTest(Connection connection, int testId, int categoryId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addCategoryToTest);
	ps.setInt(1,testId);
	ps.setInt(2, categoryId);
	ps.setInt(3, 0);
	ps.setInt(4, 0);

	ps.executeUpdate();
	ps.close();
    }

    public static JSONObject getSpecificTestCategoryDetails(Connection connection, int testId, int categoryId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveSpecificTestCategoryDetails);
	ps.setInt(1, testId);
	ps.setInt(2, categoryId);

	BeanTestCategory result = null;

	ResultSet rs = ps.executeQuery();
	if(rs.next()) {

	    result = new BeanTestCategory();
	    result.setTestId(rs.getInt("TestId"));
	    result.setCategoryId(rs.getInt("CategoryId"));
	    result.setCategoryName(rs.getString("CategoryName"));
	    result.setQuestionsPerCategory(rs.getInt("QuestionPerCategory"));
	    result.setTimePerCategory(rs.getInt("TimePerCategory"));
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();

	data.put("testId", result.getTestId());
	data.put("categoryId", result.getCategoryId());
	data.put("categoryName", result.getCategoryName());
	data.put("questionPerCategory", result.getQuestionsPerCategory());
	data.put("timePerCategory", result.getTimePerCategory());

	return data;
    }

    public static void removeCategory(Connection connection, int testId, int categoryId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.removeCategory);
	ps.setInt(1,testId);
	ps.setInt(2,categoryId);

	ps.executeUpdate();
	ps.close();
    }

    public static void saveTimeSettings(Connection connection, int testId, String testDate, String startTime, String endTime) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateTestTimeSettings);
	ps.setString(1, testDate);
	ps.setString(2, startTime);
	ps.setString(3, endTime);
	ps.setInt(4, testId);

	ps.executeUpdate();
	ps.close();
    }


    public static void saveInitDiff(Connection connection, int initDiff, int testId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateInitDiff);
	ps.setInt(1, initDiff);
	ps.setInt(2, testId);

	ps.executeUpdate();
	ps.close();
    }

    public static void saveNegMark(Connection connection, int testId, int decreaseMark) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateNegMark);
	ps.setInt(1,decreaseMark);
	ps.setInt(2,testId);

	ps.executeUpdate();
	ps.close();
    }

    public static void saveLevelMark(Connection connection, int testId, int levelId, int levelMark) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateLevelMark);
	ps.setInt(1, levelMark);
	ps.setInt(2, testId);
	ps.setInt(3, levelId);

	ps.executeUpdate();
	ps.close();
    }

    public static JSONObject getLevelMarks(Connection connection, int testId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveLevelMarks);
	ps.setInt(1,testId);

	ArrayList<BeanLevelMarks> result = new ArrayList<BeanLevelMarks>();
	BeanLevelMarks temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new BeanLevelMarks();
	    temp.setTestId(testId);
	    temp.setLevelId(rs.getInt("LevelId"));
	    temp.setMarks(rs.getInt("Marks"));

	    result.add(temp);
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();
	data.put("levelMarks", result.toArray(new BeanLevelMarks[result.size()]));

	return data;
    }

    public static JSONObject getTestCandCat(Connection connection, int testId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveTestCandidateCategories);
	ps.setInt(1, testId);

	ArrayList<BeanUserCategory> result = new ArrayList<BeanUserCategory>();
	BeanUserCategory temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new BeanUserCategory();
	    temp.setCandidateCategory(rs.getString("CandidateCategoryName"));

	    result.add(temp);
	}
	ps.close();

	JSONObject data = new JSONObject();
	data.put("candCatList",result.toArray(new BeanUserCategory[result.size()]));

	return data;
    }

    public static void removeTestCandidateCategory(Connection connection, int testId, String candidateCategoryName) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.removeTestCandidateCategory);
	ps.setInt(1, testId);
	ps.setString(2, candidateCategoryName);

	ps.executeUpdate();
	ps.close();
    }

    public static void setTestDuration(Connection connection, int testId, int testDuration) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateTestDuration);
	ps.setInt(1, testDuration);
	ps.setInt(2, testId);

	ps.executeUpdate();
	ps.close();
    }

    public static JSONObject getTestCatQuestions(Connection connection, int testId, int categoryId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveTestCatQstn);
	ps.setInt(1, testId);
	ps.setInt(2, categoryId);

	ArrayList<Question> result = new ArrayList<Question>();
	Question temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new Question();
	    temp.setQuestionId(rs.getInt("QuestionId"));
	    temp.setQuestion(rs.getString("Question"));
	    temp.setOptionA(rs.getString("OptA"));
	    temp.setOptionB(rs.getString("OptB"));
	    temp.setOptionC(rs.getString("OptC"));
	    temp.setOptionD(rs.getString("OptD"));
	    temp.setLevel(rs.getInt("LevelId"));
	    temp.setCorrectAnswer(rs.getString("CorrectAnswer"));

	    result.add(temp);
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();
	data.put("questionList",result.toArray(new Question[result.size()]));

	return data;
    }

    public static String getCategoryName(Connection connection, int categoryId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveCategoryName);
	ps.setInt(1, categoryId);

	String result = null;

	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
	    result = rs.getString("CategoryName");
	}
	rs.close();
	ps.close();

	return result;
    }

    public static void saveNewQuestion(Connection connection, int categoryId, int levelId, String question, String optA, String optB, String optC, String optD, String correctAnswer) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.AddQuestion);
	ps.setInt(1, categoryId);
	ps.setInt(2, levelId);
	ps.setString(3, question);
	ps.setString(4, optA);
	ps.setString(5, optB);
	ps.setString(6, optC);
	ps.setString(7, optD);
	ps.setString(8, correctAnswer);

	ps.executeUpdate();
	ps.close();
    }

    public static void addQuestionToTest(Connection connection, int testId, int questionId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addQstnToTest);
	ps.setInt(1,testId);
	ps.setInt(2, questionId);

	ps.executeUpdate();
	ps.close();
    }

    public static JSONObject getCategoryQuestions(Connection connection, int testId, int categoryId) throws SQLException, JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveCategoryQuestions);
	ps.setInt(1, categoryId);
	ps.setInt(2, testId);

	ArrayList<Question> result = new ArrayList<Question>();
	Question temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new Question();
	    temp.setQuestionId(rs.getInt("QuestionId"));
	    temp.setQuestion(rs.getString("Question"));
	    temp.setOptionA(rs.getString("OptA"));
	    temp.setOptionB(rs.getString("OptB"));
	    temp.setOptionC(rs.getString("OptC"));
	    temp.setOptionD(rs.getString("OptD"));
	    temp.setLevel(rs.getInt("LevelId"));
	    temp.setCorrectAnswer(rs.getString("CorrectAnswer"));

	    result.add(temp);
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();
	data.put("questionList",result.toArray(new Question[result.size()]));

	return data;
    }

    public static void removeCategoryQuestionsFromTest(Connection connection, int testId, int categoryId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.removeCategoryQstnFromTest);
	ps.setInt(1, testId);
	ps.setInt(2, categoryId);

	ps.executeUpdate();
	ps.close();
    }

    public static JSONObject getCandidateCategories(Connection connection,int testId) throws SQLException,JSONException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.retriveUserCategories);
	ps.setInt(1, testId);

	ArrayList<BeanUserCategory> result = new ArrayList<BeanUserCategory>();
	BeanUserCategory temp = null;

	ResultSet rs = ps.executeQuery();
	while(rs.next()) {

	    temp = new BeanUserCategory();
	    temp.setCandidateCategory(rs.getString(1));

	    result.add(temp);
	}
	rs.close();
	ps.close();

	JSONObject data = new JSONObject();
	data.put("candCatList", result.toArray(new BeanUserCategory[result.size()]));

	return data;
    }

    public static void addUserCategoryToTest(Connection connection, int testId, String candidateCategoryName) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addUserCategoryToTest);
	ps.setInt(1, testId);
	ps.setString(2, candidateCategoryName);

	ps.executeUpdate();
	ps.close();
    }

    public static void removeQstnFromTest(Connection connection, int testId, int questionId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.removeQuestionFromTest);
	ps.setInt(1, testId);
	ps.setInt(2, questionId);

	ps.executeUpdate();
	ps.close();
    }

    public static void updateTestDuration(Connection connection, int testId, int categoryId) throws SQLException {

	PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.updateTestDurationOnDeleteCategory);
	ps.setInt(1, testId);
	ps.setInt(2, categoryId);
	ps.setInt(3, testId);

	ps.executeUpdate();
	ps.close();
    }
}