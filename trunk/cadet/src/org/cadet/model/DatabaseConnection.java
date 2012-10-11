package org.cadet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

import org.cadet.model.Constants;

public class DatabaseConnection {

	Connection connection = null;
	
	private DatabaseConnection(){
		try{
			Class.forName(Constants.DB.driver).newInstance();
			connection = DriverManager.getConnection(Constants.DB.dburl, Constants.DB.username, Constants.DB.password);
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
