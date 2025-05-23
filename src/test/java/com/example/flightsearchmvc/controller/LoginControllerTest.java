package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Booking;
import com.example.flightsearchmvc.repository.BookingRepository;
import com.example.flightsearchmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link LoginController}.
 * Covers login, logout, registration, and post-login booking confirmation flow.
 */
public class LoginControllerTest {

    private UserService userService;
    private BookingRepository bookingRepository;
    private LoginController loginController;
    private HttpSession session;
    private Model model;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        bookingRepository = mock(BookingRepository.class);
        loginController = new LoginController(userService, bookingRepository);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }

    /**
     * Tests a successful login without any pending booking intent.
     * Expects session storage and redirect to home.
     */
    @Test
    void loginSuccess_withoutBooking_redirectsToHome() {
        when(userService.validateUser("user", "pass")).thenReturn(true);
        when(session.getAttribute("bookingIntent")).thenReturn(null);

        String view = loginController.handleLogin("user", "pass", model, session);

        verify(session).setAttribute("username", "user");
        assertEquals("redirect:/", view);
    }

    /**
     * Tests login success with a pending booking in the session.
     * Expects booking to be saved, session updated, and confirmation view returned.
     */
    @Test
    void loginSuccess_withBooking_storesBookingAndShowsConfirmation() {
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
        verify(session).removeAttribute("bookingIntent");
        verify(bookingRepository).save(any(Booking.class));
        verify(model).addAttribute(eq("username"), eq("user"));
        verify(model).addAttribute(eq("airline"), eq("UA"));
        verify(model).addAttribute(eq("origin"), eq("JFK"));
        verify(model).addAttribute(eq("destination"), eq("LAX"));
        verify(model).addAttribute(eq("departureTime"), eq("08:00"));
        verify(model).addAttribute(eq("arrivalTime"), eq("11:00"));
        verify(model).addAttribute(eq("price"), eq(299.99));

        assertEquals("confirmation", view);
    }

    /**
     * Tests login failure with invalid credentials.
     * Expects login form to be redisplayed with error message.
     */
    @Test
    void loginFailure_returnsLoginWithError() {
        when(userService.validateUser("user", "wrong")).thenReturn(false);

        String view = loginController.handleLogin("user", "wrong", model, session);

        verify(model).addAttribute(eq("error"), any());
        assertEquals("login", view);
    }

    /**
     * Tests successful user registration.
     * Expects session message set and redirect to login page.
     */
    @Test
    void registerSuccess_redirectsToLogin() {
        when(userService.addUser("newuser", "newpass")).thenReturn(true);

        String view = loginController.handleRegister("newuser", "newpass", model, session);

        verify(session).setAttribute(eq("registrationSuccess"), contains("Registration successful"));
        assertEquals("redirect:/login", view);
    }

    /**
     * Tests duplicate registration.
     * Expects register form to be redisplayed with error.
     */
    @Test
    void registerFailure_returnsRegisterWithError() {
        when(userService.addUser("existing", "pass")).thenReturn(false);

        String view = loginController.handleRegister("existing", "pass", model, session);

        verify(model).addAttribute(eq("error"), any());
        assertEquals("register", view);
    }

    /**
     * Tests logout behavior.
     * Expects session to be invalidated and redirect to login with logout message.
     */
    @Test
    void logout_invalidatesSessionAndRedirects() {
        String view = loginController.handleLogout(session);

        verify(session).invalidate();
        assertEquals("redirect:/login?logoutSuccess=true", view);
    }
}
