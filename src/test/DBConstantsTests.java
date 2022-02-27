package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import db.constants.DBAccess;
import db.constants.Relation;
import db.exceptions.InitializationFailedException;

@DisplayName("Test suite for DB constants (Relation enum and access constants)")
class DBConstantsTests {
	
	private static final String[] relations = {
		"forest",
		"state",
		"road",
		"coverage",
		"intersection",
		"sensor",
		"report",
		"worker"
	};

	@Test
	@DisplayName("Test if DB enums hold correct values")
	void dbEnumsCorrect() {
		Set<String> expected = Arrays.asList(relations).stream().collect(Collectors.toSet());
		Set<String> actual = Arrays.asList(Relation.values()).stream().map(r -> r.getTableRelationName()).collect(Collectors.toSet());
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Test if DB enums internal mapping works")
	void dbEnumsMappingCorrect() {
		Set<Relation> expected = Arrays.asList(Relation.values()).stream().collect(Collectors.toSet());
		Set<Relation> actual = Arrays.asList(relations).stream().map(n -> Relation.valueOfTableName(n)).collect(Collectors.toSet());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Test if DB access variables load not null without error")
	void dbConstantsLoad() {
		try {
			Arrays.asList(DBAccess.DB_URL, DBAccess.USERNAME, DBAccess.PASSWORD).forEach(constant -> {
				assertNotNull(constant, "Constant should be non-null");
			});
		} catch (InitializationFailedException e) {
			assertTrue(false, "There should be no errors loading in DB access constants");
		}
	}

}
