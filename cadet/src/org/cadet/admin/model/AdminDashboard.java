


package org.cadet.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;

import org.cadet.admin.bean.BeanTest;
import org.cadet.util.model.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Komal
 *
 */

public class AdminDashboard {
	public static JSONObject gettestdetail(Connection connection) throws SQLException,JSONException
	{
		 
		
	Statement stmnt = connection.createStatement();

	ArrayList<BeanTest> result = new ArrayList<BeanTest>();
	BeanTest temp = null;
	
	ResultSet rs=stmnt.executeQuery(Constants.sqlCommands.GetAdminDashboardTests);
	while(rs.next()) 
	{

		temp = new BeanTest();
		temp.setTestId(rs.getInt("TestId"));
		temp.setTestName(rs.getString("TestName"));
		temp.setTestDate(rs.getDate("TestDate"));
		temp.setTestDuration(rs.getInt("TestDuration"));
		result.add(temp);
	}
	rs.close();
	stmnt.close();

	JSONObject data = new JSONObject();
	data.put("testList",result.toArray(new BeanTest[result.size()]));
	return data;
}
}
