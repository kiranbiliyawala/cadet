package org.cadet.client.model.adaptive;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.cadet.client.model.adaptive.algorithm.Adaptive_Ability_Optimization;
import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.Constants;


/**
 * @author Varun Jamdar
 *
 */
public class AdaptiveTestDBTransactions {
	
	public AdaptiveTestDBTransactions(){
		
	}
	
	public static boolean checkTestWithinDuration(int testID) throws SQLException, Exception{
		// this method checks if the test is started in within the stipulated test duration.
		
			DatabaseConnection dbConn=DatabaseConnection.getInstance();
			Connection conn=dbConn.getDbConnection();
			PreparedStatement ps=conn.prepareStatement(Constants.sqlCommands.getTest);
			ps.setInt(0, testID);
			
			ResultSet rs=ps.executeQuery();
			if(rs.first()){
				
				Date startTime=rs.getDate(6);
				Date endTime=rs.getDate(7);
				
				long testDuration=rs.getInt(8)*60*60*1000;
				
				Date now=new Date(new java.util.Date().getTime());
				
				if((now.getTime()>startTime.getTime())&&(now.getTime()<(endTime.getTime()-testDuration))){
					return true;
				}
			}
			else{
				throw new Exception("No such Test Exists !");
			}
		return false;
	}

	public static Adaptive_Ability_Optimization generateAAO(int testId) throws SQLException, Exception{
		//this method generates an instance of Adaptive_Optimization_Class based on the testId
		
		double initialDifficulty;
		int noOfQuestions;
		
		DatabaseConnection dbConn=DatabaseConnection.getInstance();
		PreparedStatement ps=dbConn.getDbConnection().prepareStatement(Constants.sqlCommands.getTest);
		
		ps.setInt(0, testId);
		
		ResultSet rs=ps.executeQuery();
		if(rs.first()){
			initialDifficulty=(double)rs.getInt(9);
		}
		else{
			throw new Exception("No such Test Exists !");
		}
		
		ps=dbConn.getDbConnection().prepareStatement(Constants.sqlCommands.getQuestionCountOfTest);
		ps.setInt(0, testId);
		rs=null;
		rs=ps.executeQuery();
		
		if(rs.first()){
			noOfQuestions=rs.getInt(0);
		}
		else{
			throw new Exception("No such Test Exists !");
		}
				
		return new Adaptive_Ability_Optimization(Constants.adaptive.MIN_DIFFICULTY, Constants.adaptive.MAX_DIFFICULTY, initialDifficulty, noOfQuestions, Constants.adaptive.DIFFERENCE_BETWEEN_TWO_DIFFICULTIES);
	}

	public static Question fetchNextQuestion(int testId, Double difficulty, ArrayList<Integer> askedQuestions) throws SQLException, Exception {
		
		String question_marks= "";
		
		while(askedQuestions.iterator().hasNext()){
			question_marks+= "?,";
		}
		question_marks= question_marks.substring(0, question_marks.length()-1);
		
		DatabaseConnection dbConn= DatabaseConnection.getInstance();
		PreparedStatement ps= dbConn.getDbConnection().prepareStatement(Constants.sqlCommands.fetchNextQuestion1 + question_marks + Constants.sqlCommands.fetchNextQuestion2);
		ps.setInt(0, testId);
		ps.setInt(1, difficulty.intValue());
		
		for(int i=0; i<askedQuestions.size(); i++) {
			ps.setInt(i+2, askedQuestions.get(i));
		}
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.first()){
			return new Question(rs.getInt(1), rs.getInt(2), rs.getInt(10), rs.getInt(11), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		}
		else{
			throw new Exception("Questions for the given difficulty are exhausted !");
		}
	}
}
