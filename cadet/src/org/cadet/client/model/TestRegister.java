package org.cadet.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.cadet.client.bean.BeanTest;
import org.cadet.util.model.Constants;
import org.cadet.util.model.ErrorLogging;

public class TestRegister {
	Connection connection;
	
	public TestRegister (){
		try{
			Class.forName(Constants.DB.driver);
			if(Constants.DB.hasPassword){
				connection = DriverManager.getConnection(Constants.DB.dburl, Constants.DB.username, Constants.DB.password);
			}else{
				connection = DriverManager.getConnection(Constants.DB.dburl);
			}
		}catch(Exception e)
		{
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		}
	};
	
	public BeanTest[] getNotRegisterTestForUser (String CUserName, String CCatName)
	{
		ArrayList<BeanTest> test1= new ArrayList<>();
		try {
			
			PreparedStatement pst = connection.prepareStatement("select distinct a.TestName, a.TestDate, a.TestDuration, "
					 + " a.testid from test a, testcandidatecategory c  where a.TestDate >= Sysdate() " + 
					" and c.CandidateCategoryName = ? and a.TestId = c.TestId and a.TestId not in (select TestId from result where CUsername=?) order by TestDate;");
			
			
			
				pst.setString(1, CCatName);
				pst.setString(2, CUserName);
			//	System.out.println(""+pst);
				ResultSet rs= pst.executeQuery();
					
				BeanTest objTest;
			
				while(rs.next()){
					objTest= new BeanTest();
					objTest.setTestName(rs.getString(1));
					objTest.setTestDate(rs.getDate(2));
					objTest.setTestDuration(rs.getInt(3));
					objTest.setTestId(rs.getInt(4));
					test1.add(objTest);
					
				}
				connection.close();				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return test1.toArray(new BeanTest[test1.size()]);
		
	}
	
	public BeanTest[] getViewAllTestForUser (String CUserName, String CCatName)
	{
		ArrayList <BeanTest> test2 = new ArrayList<>();
		
		try {
			
				PreparedStatement pst = connection.prepareStatement("select distinct a.TestName, a.TestDate, a.TestDuration, "
						 + " a.testid,b.marks,b.attempted,b.correct from test a, result b, testcandidatecategory c  where " + 
						" c.CandidateCategoryName = ? and a.TestId = c.TestId and a.TestId = b.TestId and b.CUserName = ? order by TestDate;");
		
				pst.setString(1, CCatName);
				pst.setString(2, CUserName);
				ResultSet rs = pst.executeQuery();
				
				BeanTest objTest;
				
				while(rs.next()) {
				
					objTest = new BeanTest();
					objTest.setTestName(rs.getString(1));
					objTest.setTestDate(rs.getDate(2));
					objTest.setTestDuration(rs.getInt(3));
					objTest.setTestId(rs.getInt(4));
					
					if(rs.getInt(5)==0 && rs.getInt(6)==0 && rs.getInt(7)==0 )
						objTest.setTestDesc("Attempted");
					else
						objTest.setTestDesc("Not Attempted");
					
					test2.add(objTest);
				}
							
				connection.close();
		}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return test2.toArray(new BeanTest[test2.size()]);
		
	}
	
	
	public int setRegisterTestForUser (String CUserName, Integer TestId)
	{
	
		int i=0;
		try {
			
				PreparedStatement pst = connection.prepareStatement("insert into result (CUserName, TestId, Marks, Attempted, Correct) values (?, ?, 0, 0, 0);");
				
				pst.setString(1, CUserName);
				pst.setInt(2, TestId);
				i = pst.executeUpdate();
								
				connection.close();
		}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
			
	
} 