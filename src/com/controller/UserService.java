package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/user")
public class UserService {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.TEXT_HTML })
	@Path("/add")
	public Response addUser(
		 @FormParam("name")String name_form,
		 @FormParam("email")String email_form,
		 @FormParam("id")String id_form) {
		
		
		String name=name_form;
		String email=email_form;
		String id = id_form;
			System.out.println(name + " | " + email+ " | " + id); //change for log4j			
		
		//User Object generation
		//Save User into BD
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
			 String id = json.getString("id");
			 System.out.println(name + " | " + email+ " | " + id); //change for log4j
			
			
		}
		
		//User Object generation
		//Save User into BD
		//Send confirmation email
		//Retrieve html	
			
			
		
		return Response.status(Response.Status.CREATED)
			.entity("A new user has been created")
			.build();
	}
	

}