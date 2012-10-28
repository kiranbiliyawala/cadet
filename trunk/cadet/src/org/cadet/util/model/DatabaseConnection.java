package org.cadet.util.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


import org.cadet.util.model.Constants;

public class DatabaseConnection {

	private Connection connection;
	
	public DatabaseConnection(){
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
	
	public void connect() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		if(Constants.DB.hasPassword){
			connection = DriverManager.getConnection(Constants.DB.dburl, Constants.DB.username, Constants.DB.password);
		}else{
			connection = DriverManager.getConnection(Constants.DB.dburl);
		}
	}
	
	public void disconnect() throws SQLException{
		connection.close();
	}
	
	private static class SingletonHolder { 
        public static final DatabaseConnection INSTANCE = new DatabaseConnection();
	}
	
	public static DatabaseConnection getInstance() {
        return SingletonHolder.INSTANCE;
	}

	public Connection getDbConnection(){
		return connection;
	}
	
}
