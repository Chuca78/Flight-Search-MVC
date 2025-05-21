package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Booking;
import com.example.flightsearchmvc.repository.BookingRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

/**
 * Controller responsible for handling flight bookings.
 * Validates session, saves booking data to the H2 database,
 * and redirects to a confirmation view.
 */
@Controller
public class BookingController {

    private final BookingRepository bookingRepository;

    /**
     * Constructor-based dependency injection of the BookingRepository.
     *
     * @param bookingRepository Repository used to persist bookings
     */
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Handles the booking request submitted by a logged-in user.
     *
     * @param airline        The selected airline name
     * @param origin         Flight origin code
     * @param destination    Flight destination code
     * @param departureTime  Flight departure time
     * @param arrivalTime    Flight arrival time
     * @param price          Flight price
     * @param session        HTTP session to validate logged-in user
     * @param model          Spring Model to pass data to the view
     * @return               Confirmation page or redirect to login
     */
    @PostMapping("/book")
    public String handleBooking(
            @RequestParam String airline,
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime,
            @RequestParam double price,
            HttpSession session,
            Model model) {

        // Check if user is logged in
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // Save booking details to session
            session.setAttribute("bookingIntent", Map.of(
                "airline", airline,
                "origin", origin,
                "destination", destination,
                "departureTime", departureTime,
                "arrivalTime", arrivalTime,
                "price", price
            ));
            return "redirect:/login";
        }

        // Build Booking entity from form data
        Booking booking = new Booking();
        booking.setUsername(username);
        booking.setAirline(airline);
        booking.setOrigin(origin);
        booking.setDestination(destination);
        booking.setDepartureTime(departureTime);
        booking.setArrivalTime(arrivalTime);
        booking.setPrice(price);
        booking.setDate(LocalDate.now());

        // Persist the booking to the H2 database
        bookingRepository.save(booking);

        // Add attributes to the model for the confirmation view
        model.addAttribute("username", username);
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("formattedAirline", booking.getFormattedAirline());
        model.addAttribute("formattedDepartureTime", booking.getFormattedDepartureTime());
        model.addAttribute("formattedArrivalTime", booking.getFormattedArrivalTime());
        model.addAttribute("price", price);

        // Return the confirmation page
        return "confirmation";
    }

    /**
     * Displays a list of all bookings for the currently logged-in user.
     * If no user is logged in, redirects to the login page.
     *
     * @param session the current HTTP session containing the logged-in user
     * @param model   the model used to pass booking data to the view
     * @return the "my-bookings" view if user is authenticated, otherwise redirect to login
     */
    @GetMapping("/my-bookings")
    public String showBookings(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("bookings", bookingRepository.findByUsername(username));
        return "my-bookings";
    }
}
