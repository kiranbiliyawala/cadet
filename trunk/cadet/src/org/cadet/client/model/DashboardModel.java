package org.cadet.client.model;

import java.sql.Connection;
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
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getDashboardTests);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {

		    temp = new DashboardBean();
		    temp.setTestId(rs.getInt("TestId"));
		    temp.setTestName(rs.getString("TestName"));
		    temp.setTestDate(rs.getDate("TestDate"));
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
