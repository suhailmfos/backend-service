package com.jarvis.backend.service;


import com.jarvis.backend.model.UserEntity;
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
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return passwordEncoder.matches(password, user != null ? user.getPassword() : null);
    }
}
