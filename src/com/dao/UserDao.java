package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDao extends DBManager {
	
	/**
	 * @param User -> User Object to be persisted in bd
	 * @author Miguel Angel de Pablo
	 * <p>Save in BD an User objet with id, name and email</p>
	 * **/
	public static void create_user(User user) throws SQLException{
		DBManager dbm = new DBManager();
		
		String insert_sql = ("insert into user (user_id,user_name,user_email) values ("+user.getUser_id()+",'"+user.getUser_name()+"','"+user.getUser_email()+"')");
		System.out.println(insert_sql); //chage to log
		try {
			dbm.conn.createStatement().execute(insert_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log fatal
			
		}finally {
			dbm.conn.close();
		}
		
	}
	
	/**
	 * @param id -> user id
	 * @author Miguel Angel de Pablo
	 * <p>Check if exist an user registered</p>
	 * **/
	public static boolean exists_user(int id) throws SQLException{
		DBManager dbm = new DBManager();
		String sql =  ("select user_id from user where user_id = "+id);
		boolean result = false;
		try {
			java.sql.Statement stmt = dbm.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int counter = 0;
			while (rs.next()){
				counter++;
			}
			if(counter==1){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		}finally {
			dbm.conn.close();
			
		}
	}
	/**
	 * @param user_id -> user that post the message
	 * @param message
	 * @author Miguel Angel de Pablo
	 * <p>insert a message in message table</p>
	 * **/
	public static void post_message(int user_id, String message) throws SQLException{
		DBManager dbm = new DBManager();
		
		String insert_sql = ("insert into message (user_id,message) values ("+user_id+",'"+message+"')");
		System.out.println(insert_sql); //chage to log
		try {
			dbm.conn.createStatement().execute(insert_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log fatal
			
		}finally {
			dbm.conn.close();
		}
		
	}
	
}
