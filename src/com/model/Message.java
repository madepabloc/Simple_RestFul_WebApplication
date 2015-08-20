package com.model;

public class Message {

	private int user_id;
	private String text;
	
	
	public Message(int user_id, String text){
		
		this.user_id = user_id;
		this.text = text;
		
	}
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
