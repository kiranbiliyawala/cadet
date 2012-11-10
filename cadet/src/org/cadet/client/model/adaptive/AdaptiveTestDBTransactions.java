package org.cadet.client.model.adaptive;

import java.security.InvalidAlgorithmParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.cadet.client.bean.CategoryAdaptiveTest;
import org.cadet.client.bean.Question;
import org.cadet.client.model.NonAdaptiveTest;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;
import org.cadet.util.Exceptions.DificultyExhaustException;
import org.cadet.util.Exceptions.NoSuchTestException;
import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;


/**
 * @author Varun Jamdar
 *
 */
public class AdaptiveTestDBTransactions {
	
	public AdaptiveTestDBTransactions(){
		
	}
	
	public static boolean checkTestWithinDuration(int testID,String username) throws SQLException{
		// this method checks if the test is started in within the stipulated test duration.
		
			return NonAdaptiveTest.isAllowed(testID, username);
			
	}

	public static ArrayList<Object> getTestDetails(int testId) throws SQLException, NoSuchTestException {
		
		ArrayList<Object> testDetails=new ArrayList<Object>();
		
		DatabaseConnection dbConn=DatabaseConnection.getInstance();
		Connection conn=dbConn.getDbConnection();
		PreparedStatement ps=conn.prepareStatement(Constants.sqlCommands.getTestDetails);
		ps.setInt(1, testId);
		
		ResultSet rs=ps.executeQuery();
		rs.beforeFirst();
		if(rs.next()){
			testDetails.add(0, rs.getString(1));
			testDetails.add(1, rs.getString(2));
			testDetails.add(2, rs.getDate(3));
			testDetails.add(3, new Double(rs.getInt(4)));
		}
		else throw new NoSuchTestException("No such Test Exists !");
		
		rs.close();
		return testDetails;
	}
	
	public static HashMap<Integer, CategoryAdaptiveTest> getCategoryWiseQuestionCount(int testId) throws SQLException, NoSuchTestException{
		
		HashMap<Integer, CategoryAdaptiveTest> categories = new HashMap<Integer,CategoryAdaptiveTest>();
		CategoryAdaptiveTest category;
		
		DatabaseConnection dbConn=DatabaseConnection.getInstance();
		Connection conn=dbConn.getDbConnection();
		PreparedStatement ps=conn.prepareStatement(Constants.sqlCommands.getQuestionCountAndCategoryOfTest);
		ps.setInt(1, testId);
		ResultSet rs=ps.executeQuery();
		rs.beforeFirst();
		
		while(rs.next()){
			category=new CategoryAdaptiveTest(testId, rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			categories.put(category.getCategoryId(), category);
			
		}
		rs.close();
		if(categories.isEmpty())
			throw new NoSuchTestException("Category wise questions not defined!");
		return categories;
	}
	
	public static Adaptive_Ability_Optimization generateAAO(int testId, int noOfQuestions) throws SQLException, NoSuchTestException, InvalidAlgorithmParameterException{
		//this method generates an instance of Adaptive_Optimization_Class based on the testId
		
		double initialDifficulty;
		
		
		DatabaseConnection dbConn=DatabaseConnection.getInstance();
		PreparedStatement ps=dbConn.getDbConnection().prepareStatement(Constants.sqlCommands.getTest);
		
		ps.setInt(1, testId);
		
		ResultSet rs=ps.executeQuery();
		if(rs.first()){
			initialDifficulty=(double)rs.getInt(9);
		}
		else{
			throw new NoSuchTestException("No such Test Exists!");
		}
		rs.close();
		return new Adaptive_Ability_Optimization(Constants.adaptive.MIN_DIFFICULTY, Constants.adaptive.MAX_DIFFICULTY, initialDifficulty, noOfQuestions, Constants.adaptive.DIFFERENCE_BETWEEN_TWO_DIFFICULTIES);
	}

	public static Question fetchNextQuestion(int testId, int categoryId, Double difficulty, ArrayList<Integer> askedQuestions) throws SQLException, DificultyExhaustException {
		
		String questions_asked= "";
		Question q;
		boolean substring = false;
		for(int i=0; i<askedQuestions.size(); i++) {
			questions_asked+= askedQuestions.get(i) + ",";
			substring = true;
		}
		if(substring){
			questions_asked= questions_asked.substring(0, questions_asked.length()-1);
		}
		else{
			questions_asked = "0";
		}
		
		
		DatabaseConnection dbConn= DatabaseConnection.getInstance();
		PreparedStatement ps= dbConn.getDbConnection().prepareStatement(Constants.sqlCommands.fetchNextQuestion1 + questions_asked + Constants.sqlCommands.fetchNextQuestion2);
		ps.setInt(1, testId);
		ps.setInt(2, categoryId);
		ps.setInt(3, difficulty.intValue());
		
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.first()){
			q= new Question(rs.getInt(1), rs.getInt(2), rs.getInt(10), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		}
		else{
			throw new DificultyExhaustException("Questions for the given difficulty are exhausted !");
		}
		rs.close();
		return q;
	}
	
	public static void saveResult(String username, int testId, Double ability, int attempted, int correctAnswers) throws SQLException {
		System.out.println(ability.floatValue());
		DatabaseConnection dbConn=DatabaseConnection.getInstance();
		Connection conn=dbConn.getDbConnection();
		PreparedStatement ps=conn.prepareStatement(Constants.sqlCommands.saveResult);
		
		ps.setDouble(1, ability.doubleValue());
		ps.setInt(2, attempted);
		ps.setInt(3, correctAnswers);
		ps.setString(4, username);
		ps.setInt(5, testId);
		
		ps.execute();
	}
}
