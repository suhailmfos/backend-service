package com.jarvis.backend.repo;

import com.jarvis.backend.dto.User;
import com.jarvis.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    User findByUsernameTest(String username);
}