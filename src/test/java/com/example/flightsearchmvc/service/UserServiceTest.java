package com.example.flightsearchmvc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link UserService}.
 * Verifies the behavior for adding users, checking existence,
 * and validating credentials against users.xml.
 */
public class UserServiceTest {

    private UserService userService;

    /**
     * Sets up a fresh instance of UserService before each test.
     * Note: Uses actual file I/O via the default XML file path.
     */
    @BeforeEach
    void setup() {
        userService = new UserService();
    }

    /**
     * Adds a uniquely named user and verifies that they were successfully saved and exist.
     */
    @Test
    void testAddUserAndCheckExists() {
        String username = "junit_user_" + System.currentTimeMillis(); // Prevent name collisions
        String password = "testpass";

        boolean added = userService.addUser(username, password);
        assertTrue(added, "Expected user to be added successfully");

        boolean exists = userService.userExists(username);
        assertTrue(exists, "Expected user to exist after addition");
    }

    /**
     * Adds a user, then verifies that valid credentials are accepted.
     */
    @Test
    void testValidateUserSuccess() {
        String username = "validuser";
        String password = "validpass";
        userService.addUser(username, password);

        boolean isValid = userService.validateUser(username, password);
        assertTrue(isValid, "Expected valid credentials to return true");
    }

    /**
     * Verifies that nonexistent users return false when validated.
     */
    @Test
    void testValidateUserFailure() {
        boolean isValid = userService.validateUser("wronguser", "wrongpass");
        assertFalse(isValid, "Expected validation to fail for unknown user");
    }
}
