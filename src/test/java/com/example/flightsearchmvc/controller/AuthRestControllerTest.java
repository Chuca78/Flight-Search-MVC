package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.User;
import com.example.flightsearchmvc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

/**
 * Integration-style tests for AuthRestController.
 * Verifies API-style login and registration behavior using JSON payloads.
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthRestControllerTest {

    private final WebApplicationContext context;
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthRestControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * Initialize MockMvc before each test using application context.
     */
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * Test successful login returns HTTP 200 and user JSON.
     */
    @Test
    void login_validUser_returnsOk() throws Exception {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("pass");

        when(userService.validateUser("demo", "pass")).thenReturn(true);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.username").value("demo"));
    }

    /**
     * Test login failure returns HTTP 401 Unauthorized.
     */
    @Test
    void login_invalidUser_returnsUnauthorized() throws Exception {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("wrong");

        when(userService.validateUser("demo", "wrong")).thenReturn(false);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }

    /**
     * Test successful registration returns HTTP 201 Created.
     */
    @Test
    void register_validUser_returnsCreated() throws Exception {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("newpass");

        when(userService.addUser("newuser", "newpass")).thenReturn(true);

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User registered successfully"));
    }

    /**
     * Test duplicate registration returns HTTP 409 Conflict.
     */
    @Test
    void register_existingUser_returnsConflict() throws Exception {
        User user = new User();
        user.setUsername("existing");
        user.setPassword("any");

        when(userService.addUser("existing", "any")).thenReturn(false);

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Username already exists"));
    }
}
