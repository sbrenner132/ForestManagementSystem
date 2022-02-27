package db.exceptions;

/**
 * Exception class for handling a {@link RuntimeException} when DB credentials
 * fail to load
 * 
 * @author Sam Brenner
 *
 */
public class InitializationFailedException extends RuntimeException {
	/**
	 * Auto-generated serial version UID
	 */
	private static final long serialVersionUID = -5994010553279289001L;

	/**
	 * Constructs a {@link RuntimeException} specific to failed DB access loading
	 * 
	 * @param msg {@link String} reason this exception was thrown
	 * @param cause {@link Throwable} reason related to this exception
	 */
	public InitializationFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
