package com.lcpan.m08;

import java.sql.*;

public class DatabaseMetaDataDemo {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("Database Name: " + dbmd.getDatabaseProductName());
			System.out.println("Database Version: " + dbmd.getDatabaseProductVersion());
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("URL: " + dbmd.getURL());
			System.out.println("User Name: " + dbmd.getUserName());

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
