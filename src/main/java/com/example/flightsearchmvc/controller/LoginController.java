package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Constructor for injecting UserService dependency.
 * Spring will automatically pass the appropriate bean here.
 */
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Show registration form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Returns login.html
    }

     /**
     * Handle login form submission.
     *
     * @param username The entered username
     * @param password The entered password
     * @param model    Spring model to pass attributes to the view
     * @return Redirect to dashboard if valid, or redisplay login with error
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {
        boolean valid = userService.validateUser(username, password);

        if (valid) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Returns register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        boolean added = userService.addUser(username, password);

        if (!added) {
            model.addAttribute("error", "Username already exists.");
        } else {
            model.addAttribute("success", "Registration successful. <a href='/login'>Click here to log in.</a>");
        }

        return "register";
    }

    // Show dashboard after successful login
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // Returns dashboard.html
    }

    // Allow logout after successful login
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}