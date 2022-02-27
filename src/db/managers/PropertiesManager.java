package db.managers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.constants.Relation;

public class PropertiesManager {
	
	public static String singlePK(Relation type) {
		try {
			Connection conn = ConnectionManager.connection();
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet rs = dmd.getPrimaryKeys("", "", type.getTableRelationName());
			return rs.getString("COLUMN_NAME"); // we only care about one PK
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
