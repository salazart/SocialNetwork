package com.social.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2TrafficService {
	private String vk = "vk";
	private Connection connection = H2ConnectionService.getInstance().getConnection();

	public H2TrafficService() {
		createUser();
	}

	public void createUser() {
		String query = "CREATE TABLE IF NOT EXISTS " + vk
				+ "(ID INT NOT NULL AUTO_INCREMENT,"
				+ "ID_VK VARCHAR(255) NOT NULL DEFAULT '',"
				+ "shoved BOOLEAN DEFAULT FALSE," + " PRIMARY KEY (ID));";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(this.getClass().toString() + " " + e.getMessage());
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println(this.getClass().toString() + " " + e.getMessage());
			}
		}
	}

	public void insertUser(String id) {
		String query = "INSERT INTO "
				+ vk
				+ " (id, ID_VK, shoved)"
				+ "VALUES(DEFAULT, ?, DEFAULT);";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(this.getClass().toString() + " "
					+ e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.err.println(this.getClass().toString() + " "
						+ e.getMessage());
			}
		}
	}
	
	public String getUserId(){
		String query = "SELECT ID_VK FROM" + vk
				+ " WHERE shoved=false LIMIT 1;";
		String id = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				id = rs.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println(this.getClass().toString() + " " + e.getMessage());
		} finally {
			try {
				if(st!=null){
					st.close();
				}
			} catch (SQLException e) {
				System.out.println(this.getClass().toString() + " " + e.getMessage());
			}
		}
		return id;
	}
	
	
	public void update(String id) {
		String query = "UPDATE " + vk
				+ " SET shoved=true "
				+ "WHERE ID_VK=?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(this.getClass().toString() + " "
						+ e.getMessage());
			}
	}
}
