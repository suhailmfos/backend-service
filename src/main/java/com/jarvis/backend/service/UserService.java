package com.jarvis.backend.service;


import com.jarvis.backend.config.exceptions.UserAlreadyExistsException;
import com.jarvis.backend.dto.RegisterUserRequest;
import com.jarvis.backend.model.Authority;
import com.jarvis.backend.model.UserEntity;
import com.jarvis.backend.repo.AuthorityRepository;
import com.jarvis.backend.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterUserRequest request, String role){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("User with username '" + request.getUsername() + "' already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        Authority authority = new Authority();
        authority.setAuthority(role);
        authority.setUser(user);

        authorityRepository.save(authority);
    }
}