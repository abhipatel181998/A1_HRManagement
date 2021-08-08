package ca.humber.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static Connection connection = null;
	private static Properties properties = new Properties();

	// Method used to connect to the database using thin driver and returns
	// connection object.
	public Connection getConnectionThinDriver() {
		try {
			// loading the properties from the database.properties file.
			properties.load(new FileInputStream("src/database.properties"));

			// registering the driver
			Class.forName(properties.getProperty("driver.class.name"));

			// connecting to the database
			connection = DriverManager.getConnection(properties.getProperty("db.url.thin"),
					properties.getProperty("db.user"), properties.getProperty("db.password"));

		}
		// Handling exceptions.
		catch (IOException iEx) {
			System.out.println("Error in properties file: " + iEx.getMessage());
		} catch (ClassNotFoundException cEx) {
			System.out.println("Error in registering databse class: " + cEx.getMessage());
		} catch (SQLException sEx) {
			System.out.println("SQL Error: " + sEx.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return connection;
	}

	// Method used to connect to the database using oci driver and returns
	// connection object.
	public Connection getConnectionOciDriver() {
		try {
			// loading the properties from the database.properties file.
			properties.load(new FileInputStream("src/database.properties"));

			// registering the driver.
			Class.forName(properties.getProperty("driver.class.name"));

			// connecting to the databse.
			connection = DriverManager.getConnection(properties.getProperty("db.url.oci"),
					properties.getProperty("db.user"), properties.getProperty("db.password"));

		}
		// Handling exceptions.
		catch (IOException iEx) {
			System.out.println("Error in properties file: " + iEx.getMessage());
		} catch (ClassNotFoundException cEx) {
			System.out.println("Error in registering databse class: " + cEx.getMessage());
		} catch (SQLException sEx) {
			sEx.printStackTrace();
			System.out.println("SQL Error: " + sEx.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getSuppressed());
		}

		return connection;
	}
}
