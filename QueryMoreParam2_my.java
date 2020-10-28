package com.lcpan.m06;

import java.sql.*;

public class QueryMoreParam2_my {
	private static final String DB_URL = 
			"jdbc:mysql://localhost:3306/jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	private static final String SQL =
			"SELECT * FROM employee WHERE deptno = ? AND title = ?";
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			int deptno = 100;
			String title = "engineer";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			System.out.println(pstmt.getClass());
			pstmt.setInt(1, deptno);
			pstmt.setString(2, title);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getInt("salary"));
			}

			rs.close();
			pstmt.close();
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
