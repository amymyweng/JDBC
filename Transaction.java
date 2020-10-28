package com.lcpan.m11_12;

import java.sql.*;

public class Transaction {
	private static final String DB_URL = 
			"jdbc:mysql://localhost:3306/jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	private static final String SQL =
			"INSERT INTO department VALUES (?, ?)";
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 601);
			pstmt.setString(2, "Sales");
			pstmt.executeUpdate();
			pstmt.setInt(1, 602);
			pstmt.setString(2, "Service");
			pstmt.executeUpdate();
			pstmt.setInt(1, 603);
			pstmt.setString(2, "Production");
			pstmt.executeUpdate();
			conn.commit();

			pstmt.setInt(1, 604);
			pstmt.setString(2, "Sales1");
			pstmt.executeUpdate();
			pstmt.setInt(1, 405);
			pstmt.setString(2, "Service2");
			pstmt.executeUpdate();
			pstmt.setInt(1, 406);
			pstmt.setString(2, "Production2");
			pstmt.executeUpdate();
			conn.commit();

			conn.setAutoCommit(true);

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				System.err.println("Transaction is being rolled back");
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
