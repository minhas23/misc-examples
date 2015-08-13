package com.manjeet.sample.java.database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgreSQLExample {

	public static void main(String[] argv) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		
		try {
			  Class.forName("org.postgresql.Driver");
		    } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		   }

		

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testdb","postgres","");
		    } catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		String selectTableSQL = "SELECT name,age,country from test";

		try {
			statement = connection.createStatement();
			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				String name = rs.getString("name");
				Integer age = rs.getInt("age");
				String country = rs.getString("country");

				System.out.println("name : " + name);
				System.out.println("age : " + age);
				System.out.println("country : " + country);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
	}
}