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
 * Validates user sessions, saves booking data to the H2 database,
 * and displays confirmation or booking history views.
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
     * Handles POST requests for booking a flight. Validates login,
     * persists booking details to the database, and displays a confirmation view.
     * If the user is not logged in, redirects to login and stores the booking intent in the session.
     *
     * @param airline        The selected airline name
     * @param origin         Flight origin code (IATA)
     * @param destination    Flight destination code (IATA)
     * @param departureTime  ISO 8601 string representing flight departure time
     * @param arrivalTime    ISO 8601 string representing flight arrival time
     * @param price          Base ticket price (single passenger)
     * @param passengers     Number of passengers
     * @param session        HTTP session used to check login status and store intent
     * @param model          Spring model used to pass attributes to confirmation view
     * @return Redirect to login or confirmation page
     */
    @PostMapping("/book")
    public String handleBooking(
            @RequestParam String airline,
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime,
            @RequestParam double price,
            @RequestParam int passengers,
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            session.setAttribute("bookingIntent", Map.of(
                "airline", airline,
                "origin", origin,
                "destination", destination,
                "departureTime", departureTime,
                "arrivalTime", arrivalTime,
                "price", price,
                "passengers", passengers
            ));
            return "redirect:/login";
        }

        Booking booking = new Booking();
        booking.setUsername(username);
        booking.setAirline(airline);
        booking.setOrigin(origin);
        booking.setDestination(destination);
        booking.setDepartureTime(departureTime);
        booking.setArrivalTime(arrivalTime);
        booking.setPrice(price * passengers);
        booking.setPassengers(passengers);
        booking.setDate(LocalDate.now());

        bookingRepository.save(booking);

        model.addAttribute("booking", booking);
        model.addAttribute("username", username);
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("formattedAirline", booking.getFormattedAirline());
        model.addAttribute("formattedDepartureTime", booking.getFormattedDepartureTime());
        model.addAttribute("formattedArrivalTime", booking.getFormattedArrivalTime());
        model.addAttribute("price", price);
        model.addAttribute("passengers", passengers);

        return "confirmation";
    }

    /**
     * Displays all bookings made by the logged-in user.
     * Redirects to login page if no user is authenticated.
     *
     * @param session HTTP session containing the current user
     * @param model   Spring model to populate the booking history view
     * @return Thymeleaf view name for "my-bookings"
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
