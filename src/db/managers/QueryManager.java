package db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import db.constants.Relation;
import db.exceptions.DatabaseInsertionException;
import db.utils.QueryUtils;

public class QueryManager {
	
	public static final String INSET_QUERY = "insert into %s (%s) values (%s);";
	
	public static final String UPDATE_QUERY = "update %s set %s where %s";
	
	public static String insert(Relation type, Map<String, Object> attributes) {
		
		Connection conn = ConnectionManager.connection();
		
		String vals_wildcard = QueryUtils.wildCardGenerator(attributes.size());
		String query = QueryUtils.formatInsertQueryFromGeneric(INSET_QUERY, type, attributes.keySet(), vals_wildcard);
		
		PreparedStatement pstmt; 
		
		try {
			pstmt = conn.prepareStatement(query);
			QueryUtils.setWildCards(pstmt, attributes);
			pstmt.executeUpdate();
			return pstmt.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseInsertionException("Error inserting into database", e);
		}
	}
	
	public static String update(Relation type, Map<String, Object> attributes) {
		
		Connection conn = ConnectionManager.connection();
		
		String query = "";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			return pstmt.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseInsertionException("Error inserting into database", e);
		}
	}
	

}
