package ultravision;
/*
 * Student: Leonardo Amancio
 * Student ID: 2017401
 * Group: A
 * Subject: Object Oriented Constructs
 * Lecturer: Amilcar Aponte
 * */

import java.sql.*;
import java.util.*;

public class Database {

	private ArrayList<String> userInfo;

	protected Map<String, String> usernamePassword, fullName;
	protected String firstName = "",
			         lastName = "",
			 		 email = "",
			         username= "",
			         password = "",
					 userInput;
	boolean isConnected;

	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;

	//Creating a method that tries to connect to the database and catch possible errors.
	protected Connection getConnection() {

		String databaseManagementSystem = "mysql",
			   databaseUserName = "root",
			   databasePassword = "",
			   databaseServerName = "localhost",
		       databaseName = "ultravision";

		int databasePortNumber = 3306;

		// Connect to the database.

		connection = null;
		Properties properties = new Properties();
		properties.put("user", databaseUserName);
		properties.put("password", databasePassword);

		isConnected = false;

		try {

			if (databaseManagementSystem.equals("mysql")) {

				//Registering the Driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Getting the connection
				connection = DriverManager.getConnection(
						"jdbc:" + databaseManagementSystem + "://" +
								databaseServerName + ":" + databasePortNumber + "/" + databaseName +
								"?useUnicode=true&useJDBCCompliantTimezoneShift=" +
								"true&useLegacyDatetimeCode=false&serverTimezone=UTC",
						properties);

				isConnected = true;
				System.out.println("\nConnected to Database.\n");
			} else {
				System.out.println("Error: Database not found");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Error 3: "+"Connection not established due to the error:"+ ex + "System shutting down.");
			System.exit(-1);
		}
		return connection;
	}

	/*method to retrieve password and username from database.
	It returns "usernamePassword" Map to compare the username/password provided by the user.
	It is a way to validate the input provided.
	 */

	public Map<String, String> getUserInfo() {

		usernamePassword = new HashMap<String, String>();
		userInfo = new ArrayList<String>();

		try {
			//Query to retrieve user records
			String userQuery = "SELECT * FROM users";

			//Creating the Statement
			statement = connection.createStatement();
			//Executing the query
			resultSet = statement.executeQuery(userQuery);

				while (resultSet.next()) {

					firstName = resultSet.getString("first_name");
					lastName = resultSet.getString("last_name");
					email = resultSet.getString("email");
					username = resultSet.getString("username");
					password = resultSet.getString("password");

					usernamePassword.put(username, password);

			}statement.close();
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Error 4: "+"The system is going to close due to this error: " + e);
			System.exit(-1);
		}

		return usernamePassword;
	}

	public Map<String, String> getFullName() {
		fullName = new HashMap<String, String>();

		try {
			//Query to retrieve first and last name records based on the user input.
			String query = "SELECT first_name, last_name FROM users where username=" + "\'"+ userInput +"\'";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

				while (resultSet.next()) {

					firstName = resultSet.getString("first_name");
					lastName = resultSet.getString("last_name");

					fullName.put(firstName, lastName);
				}

		} catch (SQLException e) {
			System.out.println("Error 5: "+"The system is going to close due to this error: " + e);
			System.exit(-1);
		}
		try {resultSet.close();} catch (SQLException ex) {
			System.out.println("Error 6: "+ ex);}
		try {statement.close();} catch (SQLException e) {System.out.println("Error 7: "+e);
		}
		return fullName;

	}


}





