package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.repository.BookingRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BookingController}.
 * Verifies correct handling of booking actions under different session conditions.
 */
public class BookingControllerTest {

    private BookingRepository bookingRepository;
    private BookingController bookingController;
    private HttpSession session;
    private Model model;

    /**
     * Sets up mock dependencies and initializes the controller before each test.
     */
    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        bookingController = new BookingController(bookingRepository);
        session = mock(HttpSession.class);
        model = mock(Model.class);
    }

    /**
     * Tests that unauthenticated users are redirected to the login page
     * when attempting to book a flight.
     */
    @Test
    void redirectsToLoginIfUserNotLoggedIn() {
        when(session.getAttribute("username")).thenReturn(null); // Simulate missing login

        String result = bookingController.handleBooking(
                "UA", "JFK", "LAX", "08:00", "11:00", 299.99, session, model
        );

        assertEquals("redirect:/login", result); // Should redirect unauthenticated user
    }

    /**
     * Tests that authenticated users can successfully book a flight,
     * and that the booking is saved to the repository.
     */
    @Test
    void savesBookingAndReturnsConfirmation() {
        when(session.getAttribute("username")).thenReturn("testuser"); // Simulate authenticated user

        String result = bookingController.handleBooking(
                "UA", "JFK", "LAX", "08:00", "11:00", 299.99, session, model
        );

        verify(bookingRepository, times(1)).save(any()); // Verify save was triggered
        assertEquals("confirmation", result);            // Confirmation view should be returned
    }
}
