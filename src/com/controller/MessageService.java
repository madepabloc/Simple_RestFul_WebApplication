package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.csv.CSVPrinter;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.Util.CSVUtil;
import com.dao.MessageDao;
import com.model.Message;

@Path("/message")
public class MessageService {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getAllMessages")	
	public Response getAllMessage() throws SQLException{
		
		ArrayList<Message> message_list = MessageDao.getAllMessages();
		String text = "";
		int id = 0;
		
		JSONObject jObject = new JSONObject();
		JSONArray json_array = new JSONArray();
		try{
		for (Message m: message_list){
			JSONObject json = new JSONObject();
			text = m.getText();
			id = m.getUser_id();
			json.put("user_id", id);
			json.put("message", text);
			json_array.put(json);
		}
			jObject.put("MessageList",json_array);
		}catch (JSONException jse) {
            jse.printStackTrace();
        }
		
		return Response.status(Response.Status.CREATED)
				.entity(json_array)
				.build();
	}
	
	
	@GET
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getMessages/{user_id}")	
	public Response getAllMessage(
			@PathParam("user_id") int user_id) throws SQLException{
		
		ArrayList<Message> message_list = MessageDao.getMessagesByUser(user_id);
		String text = "";
		int id = 0;
		
		JSONObject jObject = new JSONObject();
		JSONArray json_array = new JSONArray();
		try{
		for (Message m: message_list){
			JSONObject json = new JSONObject();
			text = m.getText();
			id = m.getUser_id();
			json.put("user_id", id);
			json.put("message", text);
			json_array.put(json);
		}
			jObject.put("MessageList",json_array);
		}catch (JSONException jse) {
            jse.printStackTrace();
        }
		
		return Response.status(Response.Status.CREATED)
				.entity(json_array)
				.build();
	}
	
	
	
	@GET
	@Produces("application/octet-stream")
	@Path("/AllMessages.csv")	
	public Response export_CSV_all_messages() throws SQLException, FileNotFoundException{
		
		ArrayList<Message> message_list = MessageDao.getAllMessages();
		
		CSVUtil.writeCsvFile("..\\csvFile", message_list);
		FileInputStream fis = new FileInputStream("..\\csvFile");
		
		
		return Response.status(Response.Status.CREATED)
				.entity(fis)
				.build();
	}
	
	@GET
	@Produces("application/octet-stream")
	@Path("/{user_id}/AllMessages.csv")	
	public Response export_CSV_all_messages_from_user(@PathParam("user_id")int user_id) throws SQLException, FileNotFoundException{
		
		ArrayList<Message> message_list = MessageDao.getMessagesByUser(user_id);
		
		CSVUtil.writeCsvFile("..\\csvFile", message_list);
		FileInputStream fis = new FileInputStream("..\\csvFile");
		
		
		return Response.status(Response.Status.CREATED)
				.entity(fis)
				.build();
	}
	
	
	
}
