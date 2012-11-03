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

	public static Question fetchNextQuestion(Double difficulty, ArrayList<Integer> askedQuestions) {
		String question_marks="";
		while(askedQuestions.iterator().hasNext()){
			question_marks+="?,";
		}
		question_marks.substring(0, question_marks.length()-2);
		
		//create sql command insert multiple ? and then in for loop setInt
		
		return null;
	}
}
