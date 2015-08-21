package com.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
/**
 *
 * @author Miguel Angel de Pablo
 * Include this class into Util package and modify DBManager class
 */
public class Configuration {
 
    Properties properties = null;
 
    /** Configuration file name */
    public final static String CONFIG_FILE_NAME = "Configuration.properties";
 

    public final static String URL_BD_MYSQL = "db.url";
   
    public final static String user_BD = "db.user";
    public final static String pass_BD = "db.pass";


    
 
    private Configuration() {
        this.properties = new Properties();
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//Configuration
 
    /**
     * Singleton Pattern Implementation
     *
     * @return
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
 
    private static class ConfigurationHolder {
 
        private static final Configuration INSTANCE = new Configuration();
    }
 
    /**
     * Retorna la propiedad de configuración solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }//getProperty
}