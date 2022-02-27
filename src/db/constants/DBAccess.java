package db.constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import db.exceptions.InitializationFailedException;

/**
 * Encapsulation of all necessary data inside of static operations to conceal DB
 * access information. The credentials file should not be uploaded to any remote
 * repository, and can and should change on different machines.
 * 
 * @author Sam Brenner
 *
 */

public class DBAccess {

	/**
	 * {@link Connection} will use this URL to connect to a local or remote DB
	 */
	public static final String DB_URL;

	/**
	 * {@link Connection} will use this username to connect to a local or remote DB
	 */
	public static final String USERNAME;

	/**
	 * {@link Connection} will use this password to connect to a local or remote DB
	 */
	public static final String PASSWORD;

	static {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("credentials.txt"))));) {
			DB_URL = br.readLine();
			USERNAME = br.readLine();
			PASSWORD = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			throw new InitializationFailedException(
					"Error in extracting DB credentials from \'credentials.txt\' on root path", e);
		}
	}

}
