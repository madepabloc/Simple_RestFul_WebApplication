package com.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.Util.Email;
import com.dao.UserDao;
import com.model.User;

@Path("/user")
public class UserService {
	/**
	 * @author Miguel Angel de Pablo
	 * @param params form urlencoded
	 * Add an user from a form params
	 * **/
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.TEXT_HTML })
	@Path("/add")
	public Response addUser(
		 @FormParam("name")String name_form,
		 @FormParam("email")String email_form,
		 @FormParam("id")int id_form) {
		
		
		String name=name_form;
		String email=email_form;
		int id = id_form;
			System.out.println(name + " | " + email+ " | " + id); //change for log4j			
		
		//User Object generation
		User user = new User(id,email,name);	
		//Save User into BD
		try {
			UserDao.create_user(user);
			Email e = new Email(user.getUser_email(),"Confirmation user registered","Hi "+user.getUser_name()+" , this email confirms that you have been registered in the system. /n Thanks, /n, The Administrator");
			System.out.println("user "+user.getUser_name()+" has been registered and informed by email"); //change to log4j
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log fatal
		}
		
		//Send confirmation email
		//Retrieve html	
			
			
		
		return Response.status(Response.Status.CREATED)
			.entity("A new user has been created")
			.build();
	}
	
	
	/**
	 * @author Miguel Angel de Pablo
	 * @param JSON
	 * To use from other client, this solution is more efficient.
	 * For more time, this is a good solution using AJAX
	 * **/
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Path("/registerByJSON")
	public Response addUser(
		 String input_json) {
		System.out.println(input_json);
		if (input_json!=null){
			 JSONObject json = new JSONObject(input_json);
			 String name = json.getString("name");
			 String email = json.getString("email");
			 int id = json.getInt("id");
			 System.out.println(name + " | " + email+ " | " + id); //change for log4j
			
			
		
		
		 	//User Object generation
			User user = new User(id,email,name);	
			//Save User into BD
			try {
				UserDao.create_user(user);
				//Send confirmation email
				Email e = new Email(user.getUser_email(),"Confirmation user registered","Hi "+user.getUser_name()+" , this email confirms that you have been registered in the system. \n Thanks, \n The Administrator");
				System.out.println("user "+user.getUser_name()+" has been registered and informed by email"); //change to log4j
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//log fatal
			}
		}
		//Send confirmation email
			
			
		
		return Response.status(Response.Status.CREATED)
			.entity("A new user has been created")
			.build();
	}
	/**
	 * @author Miguel Angel de Pablo
	 * @param JSON
	 * Add a message from an specific registered user
	 * @throws SQLException 
	 * 
	 * */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Path("/postMessage")
	public Response postMessage(String input_json) throws SQLException{
		
		System.out.println(input_json);
		if(input_json!=null){
			JSONObject json = new JSONObject(input_json);
			int user_id = json.getInt("user_id");
			String message = json.getString("message");
			System.out.println(UserDao.exists_user(user_id));
			if (UserDao.exists_user(user_id)){
				//insert message in bd
				UserDao.post_message(user_id, message);
				
			}else{
				return Response.status(204).entity("Not exists any user with id "+user_id).build();
			}
			
			
		}else{
			return Response.status(400).entity("Input json has not been informed").build();
		}
		
		return Response.status(Response.Status.CREATED).entity("Message created").build();
	}
	
}