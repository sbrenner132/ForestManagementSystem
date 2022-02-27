package db.exceptions;

public class DatabaseConnectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8087685369773928839L;
	
	public DatabaseConnectionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
