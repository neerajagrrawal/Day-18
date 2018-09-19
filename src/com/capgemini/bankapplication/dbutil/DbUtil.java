package com.capgemini.bankapplication.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/customers";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			if(connection != null)
				System.out.println("--connected -- ");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("-- failed to connect --");
		}
		return connection;
	}
}
