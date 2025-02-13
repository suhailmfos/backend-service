package com.jarvis.backend.service;


import org.springframework.stereotype.Service;

@Service
public class UserService {
//    private final UserRepository userRepository;
//    private final AuthorityRepository authorityRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.authorityRepository = authorityRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    public void registerUser(RegisterUserRequest request, String role){
//        if(userRepository.findByUsername(request.getUsername()).isPresent()){
//            throw new RuntimeException("User already exists...");
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        userRepository.save(user);
//
//        Authority authority = new Authority();
//        authority.setAuthority(role);
//        authority.setUser(user);
//
//        authorityRepository.save(authority);
//    }
}