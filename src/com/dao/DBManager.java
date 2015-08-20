package com.dao;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.jersey.api.model.Parameter;

public class DBManager {
	
	private static com.Util.Parameter p = new com.Util.Parameter("..\\properties.conf");

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
////	static final String DB_URL = p.getDb_url();
//
//	   //  Database credentials -> get att from parameter class
//    static final String USER = "root"; 
//	static final String PASS = "capullo1223";
	
//	static final String USER = p.getUser(); 
//	static final String PASS = p.getPass();
	
	java.sql.Connection conn = null;
	
	public DBManager(){
		 com.Util.Parameter p = new com.Util.Parameter("..\\properties.conf");
		 String DB_URL = com.Util.Parameter.getDb_url();
		 String USER = com.Util.Parameter.getUser(); 
		 String PASS = com.Util.Parameter.getPass();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args) {
//		File f = new File("..\\properties.conf");
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(f.getAbsolutePath());
//	}

	
}
