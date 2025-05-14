package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling login, registration, and logout.
 */
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the login form.
     *
     * @return the login view
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * Handles login form submission and stores user in session.
     *
     * @param username the entered username
     * @param password the entered password
     * @param model    the Spring model
     * @param session  the HTTP session
     * @return redirect to home if valid, or redisplay login on error
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        boolean valid = userService.validateUser(username, password);

        if (valid) {
            session.setAttribute("username", username);
            return "redirect:/";
        } else {
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
        boolean added = userService.addUser(username, password);

        if (!added) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        // Auto-login after registration
        session.setAttribute("username", username);
        return "redirect:/";
    }

    /**
     * Logs out the current user and redirects to login.
     *
     * @param session the HTTP session to invalidate
     * @return redirect to login page
     */
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
