package util;

import java.sql.*;

public class SQLUtil {

	public static final String DEV_HOST = "database-1.cwv3n8ialhs8.us-east-1.rds.amazonaws.com";
	public static final String DEV_DATABASE = "DOGTrader";
	public static final String DEV_USER = "DOGTrader";
	public static final String DEV_PASSWORD = "DOGTrader";

	public static Connection getConnection() {

		String connectionString = "jdbc:postgresql://" + DEV_HOST + ":5432/" + DEV_DATABASE;
		Connection conn = null;
		try {
			System.out.println("Trying to connect...");
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(connectionString, DEV_USER, DEV_PASSWORD);
			//conn = DriverManager.getConnection()
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database.");
		}
		return conn;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
	}

	public static void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}

}
