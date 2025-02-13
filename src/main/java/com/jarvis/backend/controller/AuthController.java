package com.jarvis.backend.controller;

import com.jarvis.backend.model.AuthRequest;
import com.jarvis.backend.service.UserService;
import com.jarvis.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping()
public class AuthController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService){
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
    public ResponseEntity<Void> redirectToDocs(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

//    @PostMapping("/register/admin")
//    public ResponseEntity<String> registerAdmin(@Valid  @RequestBody RegisterUserRequest request){
//        userService.registerUser(request, "ROLE_ADMIN");
//        return ResponseEntity.ok("User registered successfully!");
//    }
//
//    @PostMapping("/register/user")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
//        userService.registerUser(request, "ROLE_USER");
//        return ResponseEntity.ok("User registered successfully!");
//    }
}