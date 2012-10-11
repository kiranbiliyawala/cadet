package org.cadet.model;

public class Constants {

	public static final String logfile = "logfile.log";
	
	
	public static final class DB{
		public static final String driver = "";
		public static final String dburl = "";
		public static final String username = "";
		public static final String password = "";	
	}
	
	public static final class sqlCommands{
		public static final String retrivePasswordAdmin = "SELECT Password FROM admin WHERE Username = "; 
		public static final String retrivePasswordClient = "SELECT Password FROM user WHERE Username = "; 
		public static final String AddClient = "INSERT INTO user (Username, Password, Name, Contact, Verified) VALUES (?, ?, ?, ?, ?)";
		public static final String AddAdmin = "INSERT INTO admin (Username, Password, Name, Contact, Verified) VALUES (?, ?, ?, ?, ?)";
		public static final String VerifyClient = "UPDATE user SET Verified='Y' WHERE Username = ?";
		public static final String VerifyAdmin = "UPDATE admin SET Verified='Y' WHERE Username = ?"; 
		public static final String CheckClientVerification = "SELECT Verified FROM user WHERE Username = ?";
		public static final String CheckAdminVerification = "SELECT Verified FROM admin WHERE Username = ?";

	}
}
