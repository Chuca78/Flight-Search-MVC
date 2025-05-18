package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit tests for the {@link LoginController}.
 * Verifies login and registration behaviors for different session and service outcomes.
 */
public class LoginControllerTest {

    private UserService userService;
    private LoginController loginController;
    private HttpSession session;
    private Model model;

    /**
     * Initializes mock dependencies before each test.
     */
    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        loginController = new LoginController(userService);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }

    /**
     * Tests successful login with valid credentials.
     */
    @Test
    void loginSuccess_redirectsToHome() {
        when(userService.validateUser("user", "pass")).thenReturn(true);

        String view = loginController.handleLogin("user", "pass", model, session);

        verify(session).setAttribute("username", "user");
        assertEquals("redirect:/", view);
    }

    /**
     * Tests login failure when credentials are invalid.
     */
    @Test
    void loginFailure_returnsLoginWithError() {
        when(userService.validateUser("user", "wrong")).thenReturn(false);

        String view = loginController.handleLogin("user", "wrong", model, session);

        verify(model).addAttribute(eq("error"), any());
        assertEquals("login", view);
    }

    /**
     * Tests successful registration and redirect to home.
     */
    @Test
    void registerSuccess_redirectsToHome() {
        when(userService.addUser("newuser", "newpass")).thenReturn(true);

        String view = loginController.handleRegister("newuser", "newpass", model, session);

        verify(session).setAttribute(eq("registrationSuccess"), contains("Registration successful"));
        assertEquals("redirect:/login", view);
    }

    /**
     * Tests registration failure when username already exists.
     */
    @Test
    void registerFailure_returnsRegisterWithError() {
        when(userService.addUser("existing", "pass")).thenReturn(false);

        String view = loginController.handleRegister("existing", "pass", model, session);

        verify(model).addAttribute(eq("error"), any());
        assertEquals("register", view);
    }

    /**
     * Tests logout by ensuring session is invalidated and redirected to login.
     */
    @Test
    void logout_invalidatesSessionAndRedirects() {
        String view = loginController.handleLogout(session);

        verify(session).invalidate();
        assertEquals("redirect:/login?logoutSuccess=true", view);
    }
    @Test
    void loginSuccess_withFlightSessionData_restoresAttributes() {
        when(userService.validateUser("user", "pass")).thenReturn(true);

        Map<String, Object> bookingIntent = new HashMap<>();
        bookingIntent.put("airline", "UA");
        bookingIntent.put("origin", "JFK");
        bookingIntent.put("destination", "LAX");
        bookingIntent.put("departureTime", "08:00");
        bookingIntent.put("arrivalTime", "11:00");
        bookingIntent.put("price", 299.99);

        when(session.getAttribute("bookingIntent")).thenReturn(bookingIntent);

        String view = loginController.handleLogin("user", "pass", model, session);

        verify(session).setAttribute("username", "user");
        verify(session).getAttribute("bookingIntent");
        verify(session).removeAttribute("bookingIntent");
        assertEquals("confirmation", view);
    }
}
