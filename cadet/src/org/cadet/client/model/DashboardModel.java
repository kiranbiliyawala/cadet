package org.cadet.client.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.cadet.client.bean.DashboardBean;
import org.cadet.util.model.Constants;
import org.json.JSONException;
import org.json.JSONObject;


public class DashboardModel {
	

	
	public static JSONObject getTests(Connection connection) throws SQLException, JSONException {

		Statement statement = connection.createStatement();

		ArrayList<DashboardBean> result = new ArrayList<DashboardBean>();
		DashboardBean temp = null;

		ResultSet rs = statement.executeQuery(Constants.sqlCommands.getDashboardTests);
		while (rs.next()) {

		    temp = new DashboardBean();
		    temp.setTestId(rs.getInt("TestId"));
		    temp.setTestName(rs.getString("TestName"));
		    temp.setTestDate(rs.getDate("TestDate"));
//		    temp.setStartTime(rs.getTimestamp("StartTime"));
//		    temp.setEndTime(rs.getTimestamp("EndTime"));
		    temp.setTestDuration(rs.getInt("TestDuration"));

		    result.add(temp);
		}
		rs.close();
		statement.close();

		JSONObject data = new JSONObject();
		data.put("testList", result.toArray(new DashboardBean[result.size()]));

		return data;
	    }
	
	
}
