package com.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Parameter {
	
	private static String db_url;
	private static String user;
	private static String pass;

	private static String path="";
	private static Logger log = (Logger) LogManager.getLogger(Parameter.class.getName());

	
public Parameter(String entrypath){
	
	path=entrypath;
	loadFileProperties(path);
}

	protected static void loadFileProperties(String path){
	
		//Carga del fichero properties												  		
		Properties prop = new Properties();									  		  	
		InputStream is = null;												  		  		
		try {	
			is=new FileInputStream(path);		  		  						  					
			prop.load(is);													  		  	
		}catch(IOException e) {												  		  
		    log.fatal("Fail in the properties file loading");
		    log.fatal("File: "+path);
			log.fatal(e);
			System.exit(1);
		}
//----------------------------------------------------------------------------------//			
			
		//Se cargan las variables del fichero de configuracion
			setDb_url(prop.getProperty("db.url"));
			setUser(prop.getProperty("db.user"));
			setPass(prop.getProperty("db.pass"));
			
			
						
	//Log
	log.info(" ");		
	log.info("----------------------------------------------------------------------------");
	log.info("OK Fichero properties "+path+ " cargado correctamente");		
//----------------------------------------------------------------------------------//
	
	
	
	
	
	
	}//cargarFicheroProperties


	public static String getDb_url() {
		return db_url;
	}

	public static void setDb_url(String db_url) {
		Parameter.db_url = db_url;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Parameter.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		Parameter.pass = pass;
	}

	

		
	
}
