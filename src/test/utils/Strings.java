package test.utils;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Set of String utilities for test suites
 * 
 * @author Sam Brenner
 *
 */

public class Strings {

	/**
	 * Default {@link String} size
	 */
	private static final int FIXED = 10;

	/**
	 * Generates a random {@link String}
	 * 
	 * @param size for internal {@code Byte} array
	 * @return random {@link String} of length size
	 */
	public static String random(int size) {
		byte[] array = new byte[size];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

	/**
	 * Generates a random {@link String} of a {@link #FIXED} size
	 * 
	 * @returnrandom {@link String} of length {@link #FIXED}
	 */
	public static String random() {
		return random(FIXED);
	}
	
	/**
	 * Generates a random {@link String} name of a {@link #FIXED} size
	 * 
	 * @returnrandom {@link String} of length {@link #FIXED}
	 */
	public static String generateRandomName() {
		return generateRandomName(FIXED);
	}
	/**
	 * Generates a random {@link String} name
	 * 
	 * @param len for internal {@code StringBuilder}
	 * @return random {@link String} name of length len
	 */
	public static String generateRandomName(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

}
