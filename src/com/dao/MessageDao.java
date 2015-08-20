package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Message;

public class MessageDao {

	
	public static ArrayList<Message> getAllMessages() throws SQLException{
		
		DBManager dbm = new DBManager();
		ArrayList<Message>messages_list = new ArrayList<Message>();
		String sql =  ("select * from message");
		boolean result = false;
		try {
			java.sql.Statement stmt = dbm.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				String text = rs.getString("message");
				int user_id = rs.getInt("user_id");
				
				Message message = new Message(user_id, text);
				messages_list.add(message);
				
			}
			
			return messages_list;
			
		} catch (SQLException e) {
		}finally {
			dbm.conn.close();
			
		}
		
		return messages_list;
		
	}

	public static ArrayList<Message> getMessagesByUser(int user_id) throws SQLException {
		DBManager dbm = new DBManager();
		ArrayList<Message>messages_list = new ArrayList<Message>();
		String sql =  ("select * from message where user_id ="+ user_id);
		boolean result = false;
		try {
			java.sql.Statement stmt = dbm.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				String text = rs.getString("message");
				int id = rs.getInt("user_id");
				
				Message message = new Message(user_id, text);
				messages_list.add(message);
				
			}
			
			return messages_list;
			
		} catch (SQLException e) {
		}finally {
			dbm.conn.close();
			
		}
		
		return messages_list;
	}
	
}
