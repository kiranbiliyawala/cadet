package org.cadet.util.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


import org.cadet.util.model.Constants;

public class DatabaseConnection {

	Connection connection;
	
	private DatabaseConnection(){
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
