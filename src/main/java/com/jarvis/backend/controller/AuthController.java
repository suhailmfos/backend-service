package com.jarvis.backend.controller;

import com.jarvis.backend.config.CustomUserDetails;
import com.jarvis.backend.config.exceptions.UserAlreadyExistsException;
import com.jarvis.backend.dto.RegisterUserRequest;
import com.jarvis.backend.model.AuthRequest;
import com.jarvis.backend.service.UserService;
import com.jarvis.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
public class AuthController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public ResponseEntity<String> redirectToDocs(HttpServletResponse response) throws IOException {
        return ResponseEntity.ok("Welcome to home page");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession(true);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // ✅ Store SecurityContext in session
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            return ResponseEntity.ok("{\"message\": \"Login Successful\"}");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterUserRequest request) {
        userService.registerUser(request, "ROLE_ADMIN");
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest request) throws Exception {
        try {
            userService.registerUser(request, "ROLE_USER");
            return ResponseEntity.ok("{\"message\": \"User registered successfully\"}");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api/v1/current-user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (securityContext == null || securityContext.getAuthentication() == null || !securityContext.getAuthentication().isAuthenticated() || "anonymousUser".equals(securityContext.getAuthentication().getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User is not authenticated\"}");
        }

        // Get the authenticated user's details
        CustomUserDetails userDetails = (CustomUserDetails) securityContext.getAuthentication().getPrincipal();

        // Store user details in the session (optional)
        session.setAttribute("user", userDetails);

        // Create a response object
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("username", userDetails.getUsername());
        userResponse.put("roles", userDetails.getAuthorities());

        return ResponseEntity.ok(userResponse);
    }
}