package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import db.constants.Relation;
import db.managers.ConnectionManager;
import db.managers.QueryManager;
import db.utils.QueryUtils;
import test.utils.Strings;

@DisplayName("Test suite for Query Manager and related utils")
@TestMethodOrder(OrderAnnotation.class)
class DBQueryManagerTests {

	private static final String BASE_INSERT = QueryManager.INSET_QUERY;
	private static final Relation TYPE = Relation.FOREST;
	private static final int WILD_SIZE = 3;
	private static final Map<String, Object> ATTRIBUTES = new HashMap<String, Object>();
	
	private static String name = "";

	@BeforeAll
	static void setUp() {
		ATTRIBUTES.put("name", (name = Strings.generateRandomName()));
		ATTRIBUTES.put("age", 20);
		ATTRIBUTES.put("height", 5.8f);
	}
	
	@AfterEach
	void reset() {
		if (ConnectionManager.isOpen()) {
			ConnectionManager.closeConnection();
		}
	}

	@Test
	@DisplayName("Tests wildcard generator util")
	@Order(1)
	void wildcardGeneratorShouldFormCorrectString() {
		String expected = "?,?,?";
		String actual = QueryUtils.wildCardGenerator(WILD_SIZE);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Tests query format util")
	@Order(2)
	void queryFormatShouldFormCorrectWildString() {
		String wild_vals = QueryUtils.wildCardGenerator(WILD_SIZE);
		String expected = "insert into forest (name,age,height) values (?,?,?);";

		String actual = QueryUtils.formatInsertQueryFromGeneric(BASE_INSERT, TYPE, ATTRIBUTES.keySet(), wild_vals);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Tests wildcard statement setting util")
	@Order(3)
	void preparedStatementShouldFormatCorrectly() throws SQLException {
		ConnectionManager.connect();

		Connection conn = ConnectionManager.connection();
		String query = QueryUtils.formatInsertQueryFromGeneric(BASE_INSERT, TYPE,
				ATTRIBUTES.keySet(), QueryUtils.wildCardGenerator(WILD_SIZE));
		PreparedStatement pstmt = conn.prepareStatement(query);
		QueryUtils.setWildCards(pstmt, ATTRIBUTES);

		String expected = "insert into forest (name,age,height) values (\'" + name + "\',20,5.8)";

		String result = pstmt.toString();
		String actual = result.substring(result.indexOf(':') + 1);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Tests valid insert query")
	@Order(4)
	void validInsertShouldWork() {
		ConnectionManager.connect();
		assertDoesNotThrow(() -> QueryManager.insert(Relation.FOREST, ATTRIBUTES));
	}
}
