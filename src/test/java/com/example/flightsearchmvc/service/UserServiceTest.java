package com.example.flightsearchmvc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link UserService}.
 * Verifies behavior for adding, validating, and checking users in the users.xml file.
 */
public class UserServiceTest {

    private UserService userService;

    /**
     * Initializes a new instance of UserService before each test.
     * Uses the default XML file path defined in the implementation.
     */
    @BeforeEach
    void setup() {
        userService = new UserService();
    }

    /**
     * Tests that a new user can be added and then verified to exist.
     * Uses a unique timestamped username to avoid collision in repeated test runs.
     */
    @Test
    void testAddUserAndCheckExists() {
        String username = "junit_user_" + System.currentTimeMillis(); // Ensure uniqueness
        String password = "testpass";

        boolean added = userService.addUser(username, password);
        assertTrue(added, "User should be added successfully");

        boolean exists = userService.userExists(username);
        assertTrue(exists, "User should now exist");
    }

    /**
     * Tests that a valid username and password combination returns true.
     * Adds the user first, then verifies login success.
     */
    @Test
    void testValidateUserSuccess() {
        String username = "validuser";
        String password = "validpass";
        userService.addUser(username, password);

        boolean isValid = userService.validateUser(username, password);
        assertTrue(isValid, "Valid user credentials should be accepted");
    }

    /**
     * Tests that invalid credentials (user not in XML) return false.
     */
    @Test
    void testValidateUserFailure() {
        boolean isValid = userService.validateUser("wronguser", "wrongpass");
        assertFalse(isValid, "Invalid credentials should not be accepted");
    }
}
