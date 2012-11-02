package org.cadet.client.model.adaptive;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
