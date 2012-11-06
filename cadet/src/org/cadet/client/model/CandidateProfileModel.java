package org.cadet.client.model;

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
import java.util.List;
import java.util.logging.Level;

import org.cadet.util.model.Constants;
import org.cadet.util.model.ErrorLogging;

public class CandidateProfileModel {
	Connection connection;
	Statement statement;
	
	public CandidateProfileModel(Connection connection){
		try{
			this.connection=connection;

		}catch(Exception e)
		{
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		}
	}
	
	public List<String> getCandidateProfileData(String username)
	{
		List<String> list = new ArrayList<String>();
		
		
		try {
			PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getCandidateProfileDataSql);
			statement.setString(1, username);
			System.out.println("username form Candidate profile model:" + username);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
			    list.add(rs.getString("Name"));
			    list.add(rs.getString("Contact"));
			    list.add(rs.getString("CandidateCategoryName"));
			}
			System.out.println("List form Candidate profile model:" + list);
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;	
	}

	public List<String> getCandidateCategory()
	{
		List<String> list = new ArrayList<String>();
		
		
		try {
			PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.getAllUserCategoriesSql);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
			    list.add(rs.getString("CandidateCategoryName"));			   
			   
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;	
	}

	
	public int updateCandidateProfileData(String username, String candidatename, String candidatecontact, String candidatecategoryname)
	{
		List<String> list = new ArrayList<String>();
		int rs = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(Constants.sqlCommands.updateCandidateProfileDataSql);
			statement.setString(1, candidatename);
			statement.setString(2, candidatecontact);
			statement.setString(3, candidatecategoryname);
			statement.setString(4, username);
			
			rs = statement.executeUpdate();
			System.out.print("success");
			
			statement.close();
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return rs;
}
//	public boolean updateCandidatePassword(String newpassword, String username){
//		
//		PreparedStatement statement=null;
//		int rCount=0;
//		boolean flag=false;
//		
//		try {
//			statement = connection.prepareStatement(Constants.sqlCommands.getPasswordClient);
//			statement.setString(1, username);
//			
//			ResultSet rs = statement.executeQuery();
//			
//			if (rs.next()) {
//				if (rs.getString("Password").equals(currentpassword)) //old password corrected
//                {
//					statement = connection.prepareStatement(Constants.sqlCommands.updateCandidatePasswordSql);
//					statement.setString(1, newpassword);
//					statement.setString(2, username);
//					rCount = statement.executeUpdate();	
//					if (rCount > 0) {
//                        System.out.println("Password Changed !!");
//                        flag=true;
//                    } else {
//                        flag = false;
//                    }
//                }
//			}
//				else
//				{
//					flag=false;
//				}
//			rs.close();
//			statement.close();
//			}catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			return flag;
//	}

	public static String Hashify(String Plaintext){
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
}

	