package com.social.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;


public class H2ConnectionService {
	private static String pathBaseStationDB = "/src/main/resources/DataBase";
	private static H2ConnectionService instance;
	private static Connection instanceConnection;
	private static Server server;
	
	
	private H2ConnectionService(){};
	
	public static H2ConnectionService getInstance() {
        if (instance == null) {
        	synchronized(H2ConnectionService.class) {
        		if(instance == null) {
        			instance = new H2ConnectionService();
        		}
        	}
        }
        
        startServer();
        
        if (instanceConnection == null) {
        	synchronized(Connection.class) {
        		if(instanceConnection == null) {
        			instanceConnection = getInstanceConnection();
        		}
        	}
        }
        return instance;
    }
	
	public static void startServer(){
		try {
			server = Server.createTcpServer().start();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void stopServer(){
		
		if(instanceConnection!=null){
			try {
				instanceConnection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(server != null){
			server.stop();
		}
	}
	
	private static Connection getInstanceConnection(){
		try {
			String fullPathDB = System.getProperty("user.dir") + pathBaseStationDB;
			String url = "jdbc:h2:file:" + fullPathDB;
			return DriverManager.getConnection(url, "sa", "");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Connection getConnection(){
		return instanceConnection;
	}
}
