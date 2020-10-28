package com.lcpan.m15;

import java.io.*;
import java.sql.*;

public class InsertCSV {
	private static final String DB_URL = 
			"jdbc:mysql://localhost:3306/jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private static final String SQL =
			"INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
	
	public static void main(String[] args) {		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			BufferedReader in = new BufferedReader(
					new FileReader("res\\empdata.csv"));
			String row;
			String[] cols;
			while ((row = in.readLine()) != null) {
				cols = row.split(",");
				for (int i = 0; i < cols.length; i++)
					pstmt.setString(i+1, cols[i]);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			in.close();
			pstmt.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}
