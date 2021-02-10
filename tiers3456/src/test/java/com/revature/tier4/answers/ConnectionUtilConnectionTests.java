package com.revature.tier4.answers;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import com.revature.assessors.RevAssess;
import com.revature.assessors.RevaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import com.revature.config.ConnectionUtil;


/**
 * prompt: Establish a connection to a deployed database using JDBC.
 */
@ExtendWith(RevAssess.class)
public class ConnectionUtilConnectionTests {

	private ConnectionUtil cu;

	@BeforeEach
	public void setup() {
		cu = ConnectionUtil.getInstance();
	}

	@RevaTest(tier = 4, points = 10)
	public void testConnection() throws SQLException {
		try {
			assertNotNull(cu.connect(), "ConnectionUtil.connect() method returned null when it should have returned an active connection.");
			assertTrue(cu.connect().isValid(5), "Either connection is not valid or connection timed out.");
		} catch (Exception e) {
			fail("Connection attempt has thrown an exception: " + e.getMessage());
		}
	}
}