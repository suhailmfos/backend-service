package com.jarvis.backend.payload;

import com.jarvis.backend.model.Authority;
import com.jarvis.backend.model.UserEntity;
import com.jarvis.backend.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));

            Authority adminRole = new Authority();
            adminRole.setAuthority("ROLE_ADMIN");
            adminRole.setUser(admin);

            admin.setAuthorities(Set.of(adminRole));
            userRepository.save(admin);
        }
    }
}
