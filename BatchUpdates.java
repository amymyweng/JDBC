package com.lcpan.m13;

import java.sql.*;

public class BatchUpdates {
	private static final String DB_URL = 
			"jdbc:mysql://localhost:3306/jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			stmt.addBatch("UPDATE employee SET salary = 62000 WHERE empno = 1001");
			stmt.addBatch("UPDATE employee SET salary = 36000 WHERE empno = 1002");
			stmt.addBatch("UPDATE employee SET salary = 80000 WHERE empno = 1003");
//			stmt.addBatch(("INSERT INTO employee (empno, salary) VALUES (2001, 44800)");
			stmt.executeBatch();

			stmt.close();
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
