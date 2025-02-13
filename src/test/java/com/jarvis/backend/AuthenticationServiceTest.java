package com.jarvis.backend;

import com.jarvis.backend.dto.User;
import com.jarvis.backend.repo.UserRepository;
import com.jarvis.backend.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;


    @Test
    public void testAuthenticate_Success(){
        String username = "user";
        String password = "password";

        User mockUser = new User("user", passwordEncoder.encode(password));

        when(userRepository.findByUsernameTest(username)).thenReturn(mockUser);

        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        boolean result = authenticationService.authenticate(username, password);
        Assertions.assertTrue(result);

        verify(userRepository).findByUsernameTest(username);

        verify(passwordEncoder).matches(password, mockUser.getPassword());
    }


    @Test
    public void testAuthenticate_Failure_WrongPassword() {
        // Arrange: Mock the user and the repository
        String username = "user";
        String password = "wrongPassword";
        User mockUser = new User("user", passwordEncoder.encode("password123"));

        // Mock the behavior of userRepository
        when(userRepository.findByUsernameTest(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(false);

        // Act: Authenticate with wrong password
        boolean result = authenticationService.authenticate(username, password);

        // Assert: Verify that authentication fails
        Assertions.assertFalse(result);

        // Verify that the repository was called
        verify(userRepository).findByUsernameTest(username);
        verify(passwordEncoder).matches(password, mockUser.getPassword());
    }

    @Test
    public void testAuthenticate_Failure_UserNotFound() {
        // Arrange: Mock the repository to return null
        String username = "nonExistentUser";
        String password = "password123";

        when(userRepository.findByUsernameTest(username)).thenReturn(null);

        // Act: Authenticate with non-existent user
        boolean result = authenticationService.authenticate(username, password);

        // Assert: Verify that authentication fails
        Assertions.assertFalse(result);

        // Verify that the repository was called
        verify(userRepository).findByUsernameTest(username);
    }
}
