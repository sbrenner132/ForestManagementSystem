package db.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulation for database table relations into enumerable data types, to
 * simplify query operation calls in upstream processes.
 * 
 * @author Sam Brenner
 *
 */

public enum Relation {

	/**
	 * Set of table relations
	 */
	FOREST, STATE, ROAD, COVERAGE, INTERSECTION, SENSOR, REPORT, WORKER;

	/**
	 * {@link String} name for this {@link Relation}, related to actual table name
	 */
	private String name;

	/**
	 * Internal mappings of {@link #name} to {@code this}
	 */
	private static final Map<String, Relation> BY_TABLE_NAME = new HashMap<String, Relation>();

	static {

		for (Relation r : values()) {
			BY_TABLE_NAME.put(r.name, r);
		}

	}

	/**
	 * Sets {@link #name} to be the lowercase version of {@link Relation} name
	 */
	private Relation() {
		this.name = this.name().toLowerCase();
	}

	/**
	 * Accessor for {@link #name}
	 * @return {@link #name}
	 */
	public String getTableRelationName() {
		return name;
	}

	/**
	 * Gets the {@link Relation} associated with the given name
	 * @param name {@link String} to lookup in internal mapping
	 * @return {@link Relation} associated with this name
	 */
	public static Relation valueOfTableName(String name) {
		return BY_TABLE_NAME.get(name);
	}

}
