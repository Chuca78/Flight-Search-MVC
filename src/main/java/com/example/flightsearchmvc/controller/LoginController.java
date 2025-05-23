package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Booking;
import com.example.flightsearchmvc.service.UserService;
import com.example.flightsearchmvc.repository.BookingRepository;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user login, logout, and registration.
 * Also restores in-progress bookings if login occurs mid-transaction.
 */
@Controller
public class LoginController {

    private final UserService userService;
    private final BookingRepository bookingRepository;

    /**
     * Constructor for injecting user and booking services.
     *
     * @param userService        Service to handle login and registration logic
     * @param bookingRepository  Repository for saving pending bookings post-login
     */
    public LoginController(UserService userService, BookingRepository bookingRepository) {
        this.userService = userService;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Displays the login form and optionally shows success messages for logout or registration.
     *
     * @param logoutSuccess Optional flag to display logout confirmation
     * @param session       Session used to retrieve registration success message
     * @param model         Spring model for view data
     * @return View name for login page
     */
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "logoutSuccess", required = false) String logoutSuccess,
                                HttpSession session,
                                Model model) {
        Object registrationSuccess = session.getAttribute("registrationSuccess");
        if (registrationSuccess != null) {
            model.addAttribute("registrationSuccess", registrationSuccess);
            session.removeAttribute("registrationSuccess");
        }

        if ("true".equals(logoutSuccess)) {
            model.addAttribute("logoutSuccess", "You have been logged out successfully.");
        }

        return "login";
    }

    /**
     * Handles login submission, validates credentials, stores user in session,
     * and optionally restores any pre-login booking in progress.
     *
     * @param username Entered username
     * @param password Entered password
     * @param model    Spring model
     * @param session  User session for authentication and intent tracking
     * @return Redirect to confirmation (if booking) or homepage
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {

        if (userService.validateUser(username, password)) {
            session.setAttribute("username", username);

            Object intent = session.getAttribute("bookingIntent");
            if (intent instanceof Map<?, ?> bookingData) {
                session.removeAttribute("bookingIntent");

                Booking booking = new Booking();
                booking.setUsername(username);
                booking.setAirline((String) bookingData.get("airline"));
                booking.setOrigin((String) bookingData.get("origin"));
                booking.setDestination((String) bookingData.get("destination"));
                booking.setDepartureTime((String) bookingData.get("departureTime"));
                booking.setArrivalTime((String) bookingData.get("arrivalTime"));
                booking.setPrice((Double) bookingData.get("price"));
                booking.setDate(LocalDate.now());

                bookingRepository.save(booking);

                // ✅ Fix: Ensure full booking object is passed to the view
                model.addAttribute("booking", booking);
                model.addAttribute("username", username);
                model.addAttribute("airline", booking.getAirline());
                model.addAttribute("origin", booking.getOrigin());
                model.addAttribute("destination", booking.getDestination());
                model.addAttribute("departureTime", booking.getDepartureTime());
                model.addAttribute("arrivalTime", booking.getArrivalTime());
                model.addAttribute("price", booking.getPrice());

                return "confirmation";
            }

            return "redirect:/";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    /**
     * Displays the user registration form.
     *
     * @return View name for registration page
     */
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    /**
     * Handles new user registration and redirects to login on success.
     *
     * @param username Desired username
     * @param password Desired password
     * @param model    Spring model for error messages
     * @param session  Session used to store registration success
     * @return Redirect or redisplay registration form
     */
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model,
                                 HttpSession session) {

        if (!userService.addUser(username, password)) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        session.setAttribute("registrationSuccess", "Registration successful! You can now log in.");
        return "redirect:/login";
    }

    /**
     * Logs out the current user and redirects to login with confirmation.
     *
     * @param session The HTTP session to invalidate
     * @return Redirect to login page with logout success flag
     */
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logoutSuccess=true";
    }
}
