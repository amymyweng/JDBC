package com.lcpan.m18;

import java.sql.*;
import javax.sql.DataSource;


public class ConnectionPoolingDemo {
	private static final String SQL =
			"SELECT ename, salary FROM employee";
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//connection pool by using DataSource
			// also called, logic connection
			// and poolable connection
			DataSource dataSource = DataSourceFactory.getDataSource(); 
			dataSource.
																	
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename"));
				System.out.println(", salary = " + rs.getInt("salary"));
			}
			rs.close();
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
