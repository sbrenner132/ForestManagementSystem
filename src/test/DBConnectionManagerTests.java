package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import db.exceptions.DatabaseConnectionException;
import db.managers.ConnectionManager;
import test.utils.Strings;

@DisplayName("Test suite for all manager related DB operations")
@TestMethodOrder(OrderAnnotation.class)
class DBConnectionManagerTests {
	
	@AfterEach
	void closeConnection() {
		if (ConnectionManager.isOpen()) {
			ConnectionManager.closeConnection();
		}
	}

	@Test
	@DisplayName("Test open connection on valid credentials")
	@Order(1)
	void testConnectionOpensOnValidCredentials() {
		assertDoesNotThrow(() -> ConnectionManager.connect(), "Should be able to connect normally");
	}
	
	@Test
	@DisplayName("Test connection object on valid connection")
	@Order(2)
	void testConnectionObjectOnValidConnection() {
		ConnectionManager.connect();
		assertNotNull(ConnectionManager.connection());
	}
	
	@Test
	@DisplayName("Test connection error on invalid credentials")
	@Order(3)
	void testConnectionErrorOnInvalidCredentials() {
		String invalidPass = Strings.random();
		assertThrows(DatabaseConnectionException.class, () -> {
			ConnectionManager.connect(invalidPass);
		}, "Should not be able to connect on an invalid password");
	}
	
	@Test
	@DisplayName("Test connection error on existing connection")
	@Order(4)
	void testConnectionErrorOnExisting() {
		ConnectionManager.connect();
		assertThrows(DatabaseConnectionException.class, () -> {
			ConnectionManager.connect();
		}, "Should not be able to open 2 connections");
	}
	
	@Test
	@DisplayName("Test connection object dropped after successful close")
	@Order(5)
	void testConnectionObjectDroppedOnClose() {
		ConnectionManager.connect();
		ConnectionManager.closeConnection();
		assertNull(ConnectionManager.connection());
		
	}

}
