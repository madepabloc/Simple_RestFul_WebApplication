package com.dao;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.jersey.api.model.Parameter;

public class DBManager {
	

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	java.sql.Connection conn = null;
	
	public DBManager(){
		File f = new File(System.getProperty("user.dir"));
		File fichero = new File (f.getParent()+"\\properties.conf");
		 com.Util.Parameter p = new com.Util.Parameter(fichero.getAbsolutePath());
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
//		File f = new File(System.getProperty("user.dir"));
//		File fichero = new File (f.getParent()+"\\properties.conf");
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(fichero.getAbsolutePath());
//	}

	
}
