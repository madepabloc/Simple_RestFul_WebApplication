package com.model;

public class User {
	private int user_id;
	private String user_email;
	private String user_name;
	
	
	public User(int id,String email,String name){
		
		this.user_id = id;
		this.user_name = name;
		if (email.contains("@")&&email.contains(".")){
			this.user_email = email;
			
		}else{
			System.out.println("Error, email is not valid"); //change to log4j error
			this.user_email = "fakes_email@gmail.com";
		}
		
		
		
		
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
