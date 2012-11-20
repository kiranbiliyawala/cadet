package org.cadet.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.cadet.admin.bean.BeanUserCategory;
import org.cadet.util.model.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class UserCategoryModel {
	Connection connection;
	Statement statement;
	public UserCategoryModel() {
		// TODO Auto-generated constructor stub

	    }

	
	public static JSONObject getAllUserCategory(Connection connection) throws SQLException, JSONException {
	{
		ArrayList<BeanUserCategory> test1= new ArrayList<>();
		try {
			
			PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getAllUserCategoriesSql);
			ResultSet rs = statement.executeQuery();
			
			int i =0;
			
				while(rs.next()){
					BeanUserCategory beanobj =null;
					beanobj= new BeanUserCategory();
					beanobj.setCandidateCategory(rs.getString("CandidateCategoryName"));
					System.out.println(++i+" "+rs.getString("CandidateCategoryName"));
					test1.add(beanobj);
					
				}
				rs.close();
				statement.close();
				
//				connection.close();				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject data = new JSONObject();
		data.put("UserCategoryList", test1.toArray(new BeanUserCategory[test1.size()]));
		return data;
	}
	}
	public static void addNewUserCategory(Connection connection, String UserCategory)
	{
		try{
		PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.addUserCategoriesSql);
		ps.setString(1, UserCategory);
		ps.executeUpdate();
		ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteUserCategory(Connection connection, String UserCategory)
	{
		try{
		PreparedStatement ps = connection.prepareStatement(Constants.sqlCommands.deleteCandidateCategorySql);
		ps.setString(1, UserCategory);
		ps.executeUpdate();
		ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
