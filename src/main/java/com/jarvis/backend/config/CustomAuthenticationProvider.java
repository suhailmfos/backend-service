//package com.jarvis.backend.config;
//
//import com.jarvis.backend.model.UserEntity;
//import com.jarvis.backend.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        if (passwordEncoder.matches(password, userDetails.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        } else {
//            throw new AuthenticationException("Invalid credentials") {
//            };
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//    @Service
//    public static class AuthenticationService {
//
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private BCryptPasswordEncoder passwordEncoder;
//
//        public boolean authenticate(String username, String password) {
//            UserEntity user = userRepository.findByUsername(username).orElse(null);
//            return passwordEncoder.matches(password, user != null ? user.getPassword() : null);
//        }
//    }
//}