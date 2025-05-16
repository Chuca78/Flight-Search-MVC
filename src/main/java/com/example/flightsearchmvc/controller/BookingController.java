package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Booking;
import com.example.flightsearchmvc.repository.BookingRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

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

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        // Save to H2
        Booking booking = new Booking();
        booking.setUsername(username);
        booking.setAirline(airline);
        booking.setOrigin(origin);
        booking.setDestination(destination);
        booking.setDepartureTime(departureTime);
        booking.setArrivalTime(arrivalTime);
        booking.setPrice(price);
        booking.setDate(LocalDate.now());

        bookingRepository.save(booking);

        // Add confirmation attributes
        model.addAttribute("username", username);
        model.addAttribute("airline", airline);
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("departureTime", departureTime);
        model.addAttribute("arrivalTime", arrivalTime);
        model.addAttribute("price", price);

        return "confirmation";
    }
}
