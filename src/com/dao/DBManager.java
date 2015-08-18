package com.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	   //  Database credentials -> get att from parameter class
    static final String USER = "root"; 
	static final String PASS = "capullo1223";
	
	java.sql.Connection conn = null;
	
	public DBManager(){
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log fatal
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log fatal
		}
		
	}

	
}
