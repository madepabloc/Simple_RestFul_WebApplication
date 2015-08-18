package com.dao;

import java.sql.SQLException;

import com.model.User;
import com.mysql.jdbc.PreparedStatement;

public class UserDao extends DBManager {

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
	
}
