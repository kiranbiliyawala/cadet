package org.cadet.model;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ErrorLogging {
	
	Logger logger;
	FileHandler Fhandle;
	
	private static class SingletonHolder { 
        public static final ErrorLogging INSTANCE = new ErrorLogging();
	}

	public static ErrorLogging getInstance() {
        return SingletonHolder.INSTANCE;
	}
	
	private ErrorLogging(){
		logger = Logger.getLogger("cadet_errors");
		try {
			Fhandle = new FileHandler(Constants.logfile);
			logger.addHandler(Fhandle);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(Level level,Throwable e){
	logger.log(level,new Date().toString(),e);	
	}
	
}
