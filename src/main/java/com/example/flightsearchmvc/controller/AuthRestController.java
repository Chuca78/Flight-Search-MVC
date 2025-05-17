package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.User;
import com.example.flightsearchmvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for handling user authentication and registration.
 * Designed to return JSON responses and follow RESTful conventions.
 */
@RestController
@RequestMapping("/api")
public class AuthRestController {

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Authenticates a user based on JSON-formatted credentials.
     *
     * @param user A user object containing username and password
     * @return 200 OK if credentials are valid, 401 Unauthorized otherwise
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean valid = userService.validateUser(user.getUsername(), user.getPassword());
        if (valid) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "username", user.getUsername()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Invalid credentials"
            ));
        }
    }

    /**
     * Registers a new user using JSON input.
     *
     * @param user A user object containing username and password
     * @return 201 Created if successful, 409 Conflict if username exists
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        boolean success = userService.addUser(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "User registered successfully"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "error", "Username already exists"
            ));
        }
    }
}
