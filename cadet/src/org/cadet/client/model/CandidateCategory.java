package org.cadet.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.cadet.client.bean.DashboardBean;
import org.cadet.util.model.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class CandidateCategory {
	DashboardBean temp = null;
	private static String candidatecategory=null;
	
	public static String fetch(Connection connection, String username) throws SQLException {
		DashboardBean bean = new DashboardBean();
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getCandidateCategory);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		rs.first();
		candidatecategory = rs.getString("CandidateCategoryName");
		bean.setCandidatecategory(candidatecategory);
		rs.close();
		statement.close();
		return candidatecategory;
	}
}
