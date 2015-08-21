package com.dao;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Util.Configuration;
import com.sun.jersey.api.model.Parameter;

public class DBManager {
	

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	java.sql.Connection conn = null;
	
	public DBManager(){
		
		Configuration conf = Configuration.getInstance();
		 
		 String db_url = conf.getProperty(Configuration.URL_BD_MYSQL);
		 String user =  conf.getProperty(Configuration.user_BD);
		 String pass = conf.getProperty(Configuration.pass_BD);
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(db_url,user,pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	
}
