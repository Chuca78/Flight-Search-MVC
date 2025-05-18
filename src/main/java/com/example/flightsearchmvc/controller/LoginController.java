package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling login, registration, and logout.
 */
@Controller
public class LoginController {

    private final UserService userService;

    /**
     * Constructor-based dependency injection for UserService.
     *
     * @param userService the user service to handle login/registration logic
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the login form and optionally shows messages if redirected
     * after successful registration or logout.
     *
     * @param logoutSuccess optional query parameter to trigger a logout message
     * @param session       HTTP session to retrieve success messages
     * @param model         model used to pass attributes to the view
     * @return the login view
     */
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "logoutSuccess", required = false) String logoutSuccess,
                            HttpSession session,
                            Model model) {
        // Check if there's a success message from registration
        Object registrationSuccess = session.getAttribute("registrationSuccess");
        if (registrationSuccess != null) {
            model.addAttribute("registrationSuccess", registrationSuccess);
            session.removeAttribute("registrationSuccess");  // Clear it after one-time use
        }
        if ("true".equals(logoutSuccess)) {
            model.addAttribute("logoutSuccess", "You have been logged out successfully.");
        }
            return "login";
        }

    /**
     * Handles login form submission, validates credentials, stores username
     * in session, and checks for any in-progress flight booking.
     *
     * If a booking intent is found in the session, the user is redirected to
     * the confirmation view with booking details.
     *
     * @param username the entered username
     * @param password the entered password
     * @param model    the Spring model
     * @param session  the HTTP session
     * @return confirmation view if booking in progress, home otherwise
     */

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {

        // Validate user credentials
        boolean valid = userService.validateUser(username, password);

        if (valid) {
            // Store username in session on success
            session.setAttribute("username", username);

        // Check if user had a pending booking
            Object intent = session.getAttribute("bookingIntent");

            if (intent instanceof Map<?, ?> bookingData) {
                session.removeAttribute("bookingIntent");

                // Add attributes to the model for confirmation page
                model.addAttribute("username", username);
                model.addAttribute("airline", bookingData.get("airline"));
                model.addAttribute("origin", bookingData.get("origin"));
                model.addAttribute("destination", bookingData.get("destination"));
                model.addAttribute("departureTime", bookingData.get("departureTime"));
                model.addAttribute("arrivalTime", bookingData.get("arrivalTime"));
                model.addAttribute("price", bookingData.get("price"));

                return "confirmation"; // You could also redirect to a new controller for booking creation
            }

            // No booking in progress — go to main page
            return "redirect:/";

        } else {
            // Return to login with error message
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    /**
     * Displays the registration form.
     *
     * @return the register view
     */
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    /**
     * Handles registration form submission.
     *
     * @param username the desired username
     * @param password the desired password
     * @param model    the Spring model
     * @param session  the HTTP session for optional login
     * @return redirect to search if success; redisplay form if duplicate
     */
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model,
                                 HttpSession session) {

        // Try to add user; check for duplicates
        boolean added = userService.addUser(username, password);

        if (!added) {
            // Show error if username is taken
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        // Redirect to login after successful registration
        session.setAttribute("registrationSuccess", "Registration successful! You can now log in.");
        return "redirect:/login";
    }

    /**
     * Logs out the current user and redirects to login.
     *
     * @param session the HTTP session to invalidate
     * @return redirect to login page
     */
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();  // Clear all session data
        return "redirect:/login?logoutSuccess=true";
    }
}
