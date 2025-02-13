package com.jarvis.backend.service;


import com.jarvis.backend.dto.User;
import com.jarvis.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsernameTest(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;  // Authentication successful
        }
        return false;  // Authentication failed
    }
}
