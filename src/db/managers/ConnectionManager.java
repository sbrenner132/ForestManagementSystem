package db.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.constants.DBAccess;
import db.exceptions.DatabaseConnectionException;

/**
 * Class managing the connection options to the database via a {@link DriverManager}
 * @author Sam Brenner
 *
 */

public class ConnectionManager {
	
	/**
	 * Database {@link Connection}
	 */
	private static Connection conn;
	
	/**
	 * Connects to the DB on the system password
	 */
	public static synchronized void connect() {
		connect(DBAccess.PASSWORD);
	}
	
	/**
	 * Connects to the DB on a given password
	 * @param pass {@link String} of passowrd to use to connect
	 */
	public static synchronized void connect(String pass) {
		if (isOpen()) {
			throw new DatabaseConnectionException("Databse connection is already open", null);
		} else {
			try {
				conn = DriverManager.getConnection(DBAccess.DB_URL, DBAccess.USERNAME, pass);
			} catch (SQLException e) {
				throw new DatabaseConnectionException("Could no connect to database", e);
			} 
		}
	}
	
	/**
	 * Closes the connection to the DB
	 */
	public static synchronized void closeConnection() {
		if (!isOpen()) {
			throw new DatabaseConnectionException("Databse connection not open", null);
		} else {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				throw new DatabaseConnectionException("Could not close database connection", e);
			}
		}
	}
	
	/**
	 * Accessor for the {@link Connection} object
	 * @return {@link Connection}
	 */
	public static Connection connection() {
		return conn;
	}
	
	/**
	 * Determines if there is an open {@link Connection}
	 * @return {@link true} if there is an established {@link Connection}
	 */
	public static boolean isOpen() {
		return conn != null;
	}

}
