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
	 * To use from other client, this solution is more efficient.
	 * For more time, this is a good solution using AJAX
	 * **/
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Path("/addByJSON")
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
				UserDao.create_user(user);
				Email e = new Email(user.getUser_email(),"Confirmation user registered","Hi "+user.getUser_name()+" , this email confirms that you have been registered in the system. \n Thanks, \n The Administrator");
				System.out.println("user "+user.getUser_name()+" has been registered and informed by email"); //change to log4j
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//log fatal
			}
		}
		//Send confirmation email
		//Retrieve html	
			
			
		
		return Response.status(Response.Status.CREATED)
			.entity("A new user has been created")
			.build();
	}
	

}