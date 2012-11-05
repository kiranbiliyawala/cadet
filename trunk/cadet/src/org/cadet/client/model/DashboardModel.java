package org.cadet.client.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.cadet.client.bean.DashboardBean;
import org.cadet.util.model.Constants;
import org.json.JSONException;
import org.json.JSONObject;


public class DashboardModel {
	

	
	public static JSONObject getTests(Connection connection, String username) throws SQLException, JSONException {

		

		ArrayList<DashboardBean> result = new ArrayList<DashboardBean>();
		DashboardBean temp = null;
		
		String candidatecategory  = CandidateCategory.fetch(connection, username);
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getDashboardTests);
		
		
		statement.setString(1, candidatecategory);
		statement.setString(2, username);
		
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			temp = new DashboardBean();
		    temp.setTestId(rs.getInt("TestId"));
		    temp.setTestType(rs.getString("NonAdaptive"));
		    temp.setTestName(rs.getString("TestName"));
		    temp.setTestDate(rs.getDate("TestDate"));
		    temp.setTestDuration(rs.getInt("TestDuration"));
		    if(!(NonAdaptiveTest.isAllowed(rs.getInt("TestId"), username)))
		    {
		    	temp.setGivetest("true");
		    }
		    else
		    {
		    	temp.setGivetest(null);
		    }
		    result.add(temp);
		    
		}
		rs.close();
		statement.close();

		JSONObject data = new JSONObject();
		data.put("testList", result.toArray(new DashboardBean[result.size()]));
		
		return data;
	    }
	
	
}
