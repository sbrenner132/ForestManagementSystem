package db.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import db.constants.Relation;
import db.managers.PropertiesManager;

public class QueryUtils {
	
	public static String formatInsertQueryFromGeneric(String base, Relation type, Set<String> names, String vals_wild) {
		String set = names.toString();
		String cols = set.substring(1, set.length() - 1);
		return String.format(base, type.getTableRelationName(), cols.replace(" ", ""), vals_wild);
	}
	
	public static String formatUpdateQueryFromGeneric(String base, Relation type) {

		String PK = PropertiesManager.singlePK(type);
		
		return null;
	}

	public static void setWildCards(PreparedStatement pstmt, Map<String, Object> attributes) {
		int index = 1;
		for (String key : attributes.keySet()) {
			Object val = attributes.get(key);
			try {
				preparedStatementSet(pstmt, index, val);
			} catch (SQLException e) {
				
			}
			index++;
		}
	}

	public static void preparedStatementSet(PreparedStatement p, int index, Object val) throws SQLException {
		if (Integer.class.isAssignableFrom(val.getClass())) {
			p.setInt(index, (int) val);
		} else if (Float.class.isAssignableFrom(val.getClass())) {
			p.setFloat(index, (float) val);
		} else {
			p.setString(index, (String) val);
		}
	}
	

	public static String wildEqualityGenerator(int length) {
		return repeater(length, "- = ?", ",");
	}

	public static String wildCardGenerator(int length) {
		return repeater(length, "?", ",");
	}
	
	private static String repeater(int length, String rep, String sep) {
		if (length < 0) throw new IllegalArgumentException();
		return String.join(sep, new String(new char[length]).replace("\0", rep).split(""));
	}

}
