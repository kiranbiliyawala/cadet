package org.cadet.util.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

public class UserControl {

	public static Boolean checkAdminTable(Connection connection,String username,String password) throws SQLException{
		if(password==null) return false;
		Boolean ret = false;
		String dbpassword="";
		Statement statement=null;;
		ResultSet rs=null;
		try{
			statement = connection.createStatement();
		rs = statement.executeQuery(Constants.sqlCommands.retrivePasswordAdmin+"'"+username+"'");
		int count = 0;
		while(rs.next()){
		dbpassword = rs.getString("Password");
		count++;
		}if(count==0) ret = false;
		}catch(SQLException e){
			throw e;
		}finally{
			if(rs!=null) rs.close();
			if(statement!=null) statement.close();
		}
		if(VerifyPasswords(password, dbpassword))
			{
			ret = true;
			}
		return ret;
	}
	

	public static Boolean checkClientTable(Connection connection,String username,String password) throws SQLException{
		if(password==null) return false;
		Boolean ret = false;
		String dbpassword="";
		Statement statement=null;;
		ResultSet rs=null;
		try{
			statement = connection.createStatement();
		rs = statement.executeQuery(Constants.sqlCommands.retrivePasswordClient+"'"+username+"'");
		int count = 0;
		while(rs.next()){
		dbpassword = rs.getString("Password");
		count++;
		}if(count==0) ret = false;
		}catch(SQLException e){
			throw e;
		}finally{
			if(rs!=null) rs.close();
			if(statement!=null) statement.close();
		}
		if(VerifyPasswords(password, dbpassword))
			{
			ret = true;
			}
		return ret;
	}
	
	public static Boolean isClientAvailable(Connection connection,String username) throws SQLException{
		Boolean ret=false;
		Statement statement = null;
		ResultSet rs = null;
		try{
			statement = connection.createStatement();
			rs = statement.executeQuery(Constants.sqlCommands.isClientAvailable+"'"+username+"'");
			rs.first();
			int count = rs.getInt("count");
			if(count==0){
				ret=true;
			}
		}catch(SQLException e){
			throw e;
		}finally{
			if(rs!=null) rs.close();
			if(statement!=null) statement.close();
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
	
	public static String getHashEmail(String text){
		return Hashify(text).substring(0, 20);
	}
	
	protected static String Hashify(String Plaintext){
		MessageDigest md;
		String Hash=null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		Hash = new String(md.digest(Plaintext.getBytes()),Charset.forName("UTF-8"));
		Hash = URLEncoder.encode(Hash, "UTF-8");
		Hash = Hash.replace("%", "");
		}catch(NoSuchAlgorithmException e){
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Hash;
	}
	
	public static void AddClient(Connection connection,String username,String password,String name,String Contact,String Category) throws SQLException{
		String PasswordHash = Hashify(password);
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.AddClient);
		statement.setString(1, username);
		statement.setString(2, PasswordHash);
		statement.setString(3, name);
		statement.setString(4, Contact);
		statement.setString(5, Category);
		statement.setString(6, "N");
		statement.executeUpdate();
		statement.close();
	}

	public static void UpdateClientPassword(Connection connection,String username,String password) throws SQLException{
		String PasswordHash = Hashify(password);
		
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.UpdateClientPassword);
		statement.setString(1, PasswordHash);
		statement.setString(2,username);
		statement.executeUpdate();
		statement.close();

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
		statement.close();
	}
	
	public static void VerifyClient(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.VerifyClient);
		statement.setString(1, username);
		statement.executeUpdate();
		statement.close();
	}
	
	public static void VerifyAdmin(Connection connection,String username) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.VerifyAdmin);
		statement.setString(1, username);
		statement.executeUpdate();
		statement.close();
	}
	
	public static Boolean CheckClientVerification(Connection connection,String username) throws SQLException{
		boolean ret = false;
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.CheckClientVerification);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		rs.first();
		if(rs.getString("Verified").equals("Y")){
			ret = true;
		}
		rs.close();
		statement.close();
		return ret;
	}
	
	public static Boolean CheckAdminVerification(Connection connection,String username) throws SQLException{
		boolean ret = false;
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.CheckAdminVerification);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		rs.first();
		if(rs.getString("Verified").equals("Y")){
			ret = true;
		}
		rs.close();
		statement.close();
		return ret;
	}


	public static boolean hasCategory(Connection connection, String category) throws SQLException {
		boolean ret = false;
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.hasCategory);
		statement.setString(1, category);
		ResultSet rs = statement.executeQuery();
		rs.first();
		if(rs.getInt("count")>0){
			ret = true;
		}
		rs.close();
		statement.close();
		return ret;
	}
	
	public static ArrayList<String> getUserCategories(Connection connection) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getUserCategories);
		ResultSet rs = statement.executeQuery();
		ArrayList<String> Al = new ArrayList<>();
		while (rs.next()) {
			String category = rs.getString("Category");
			Al.add(category);
		}
		rs.close();
		statement.close();
		return Al;
	}
	
}
