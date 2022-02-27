package db.exceptions;

public class DatabaseInsertionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7102654687776548932L;
	
	public DatabaseInsertionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
