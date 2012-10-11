package org.cadet.util.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class UserControl {

	public static Boolean checkAdminTable(Connection connection,String username,String password) throws SQLException{
		Boolean ret = false;
		String dbpassword=null;
		Statement statement=null;;
		ResultSet rs=null;
		try{
			statement = connection.createStatement();
		rs = statement.executeQuery(Constants.sqlCommands.retrivePasswordAdmin+"'"+username+"'");
		dbpassword = rs.getString("Password");
		}catch(SQLException e){
			throw e;
		}finally{
			if(statement!=null) statement.close();
			if(rs!=null) rs.close();
		}
		if(VerifyPasswords(password, dbpassword))
			{
			ret = true;
			}
		return ret;
	}
	

	public static Boolean checkClientTable(Connection connection,String username,String password) throws SQLException{
		Boolean ret = false;
		String dbpassword=null;
		Statement statement=null;;
		ResultSet rs=null;
		try{
			statement = connection.createStatement();
		rs = statement.executeQuery(Constants.sqlCommands.retrivePasswordClient+"'"+username+"'");
		dbpassword = rs.getString("Password");
		}catch(SQLException e){
			throw e;
		}finally{
			if(statement!=null) statement.close();
			if(rs!=null) rs.close();
		}
		if(VerifyPasswords(password, dbpassword))
			{
			ret = true;
			}
		return ret;
	}
	
	private static Boolean VerifyPasswords(String Password1,String Password2){
		String Temppassword;
		Temppassword = Hashify(Password1);
		if(Temppassword.equals(Password2)){
			return true;
		}else {
			return false;
		}
	}
	
	protected static String Hashify(String Plaintext){
		MessageDigest md;
		String Hash=null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		Hash = new String(md.digest(Plaintext.getBytes()));
		}catch(NoSuchAlgorithmException e){
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		}
		return Hash;
	}
	
	public static void AddClient(Connection connection,String username,String password,String name,String Contact) throws SQLException{
		String PasswordHash = Hashify(password);
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.AddClient);
		statement.setString(1, username);
		statement.setString(2, PasswordHash);
		statement.setString(3, name);
		statement.setString(4, Contact);
		statement.setString(5, "N");
		statement.executeUpdate();
	}
	
	public static void AddAdmin(Connection connection,String username,String password,String name,String Contact) throws SQLException{
		String PasswordHash = Hashify(password);
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.AddAdmin);
		statement.setString(1, username);
		statement.setString(2, PasswordHash);
		statement.setString(3, name);
		statement.setString(4, Contact);
		statement.setString(5, "N");
		statement.executeUpdate();
	}
	
	public static void VerifyClient(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.VerifyClient);
		statement.setString(1, username);
		statement.executeUpdate();
	}
	
	public static void VerifyAdmin(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.VerifyAdmin);
		statement.setString(1, username);
		statement.executeUpdate();
	}
	
	public static Boolean CheckClientVerification(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.CheckClientVerification);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		if(rs.getString("Verified").equals("Y")){
			return true;
		}
		return false;
	}
	
	public static Boolean CheckAdminVerification(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.CheckAdminVerification);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		if(rs.getString("Verified").equals("Y")){
			return true;
		}
		return false;
	}
}
