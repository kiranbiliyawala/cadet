package org.cadet.admin.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.cadet.admin.bean.BeanCompleteTests;
import org.cadet.admin.bean.ResultBean;
import org.cadet.util.model.Constants;


public class Result {

	
	public static ResultBean getResult(Connection connection,String testid) throws SQLException{
		
		PreparedStatement stmt = connection.prepareStatement(Constants.sqlCommands.GetResult);
		stmt.setInt(1, Integer.parseInt(testid));
		
		ResultSet rs = stmt.executeQuery();
		
		ResultBean result = new ResultBean();
		
		while(rs.next()){
			result.addScore(rs.getString("CUserName"), rs.getDouble("Marks"), rs.getInt("Attempted"), rs.getInt("Correct"));
		}
		return result;
	}
	
	public static BeanCompleteTests getTests(Connection connection) throws SQLException{
		
		PreparedStatement stmt = connection.prepareStatement(Constants.sqlCommands.GetCompleteTests);
		ResultSet rs = stmt.executeQuery();
		
		BeanCompleteTests result = new BeanCompleteTests();
		
		while(rs.next()){
			result.addTest(rs.getString("TestName"), rs.getDate("TestDate"), rs.getInt("TestDuration"), rs.getString("TestType"), rs.getInt("TestId"));
		}
		return result;
	}
	
}
