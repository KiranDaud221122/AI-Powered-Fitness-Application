package com.fitness.userservice.services;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId("1");
        user.setEmail("user@gmail.com");
        user.setPassword("password");
        user.setFirstName("Kiran");
        user.setLastName("Daud");
        user.setKeycloakId("key123");

        registerRequest = new RegisterRequest();
        registerRequest.setEmail("user@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setFirstName("Kiran");
        registerRequest.setLastName("Daud");
        registerRequest.setKeycloakId("key123");
    }

    // ---------------- register() ----------------

    @Test
    void registerUser_userShouldBeRegistered() {

        // email does NOT exist
        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserResponse response = userService.register(registerRequest);

        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("user@gmail.com", response.getEmail());
        assertEquals("Kiran", response.getFirstName());

        System.out.println(response);
    }

    @Test
    void registerUser_existingUserShouldBeReturned() {

        when(userRepository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(true);

        when(userRepository.findByEmail(registerRequest.getEmail()))
                .thenReturn(user);

        UserResponse response = userService.register(registerRequest);

        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("user@gmail.com", response.getEmail());
        System.out.println(response);
    }

    // ---------------- getUserProfile() ----------------

    @Test
    void getUserProfile_userFound() {

       when(userRepository.findById("1"))
               .thenReturn(Optional.of(user));

        UserResponse response = userService.getUserProfile("1");

        assertEquals("1", response.getId());
        assertEquals("user@gmail.com", response.getEmail());

        System.out.println(response);
    }

    @Test
    void getUserProfile_userNotFound() {

        when(userRepository.findById("1"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.getUserProfile("1")
        );

        assertEquals(" User not found", exception.getMessage());
    }

    // ---------------- existByUserId() ----------------

    @Test
    void existByUserId_shouldReturnTrue() {

        when(userRepository.existsByKeycloakId("key123"))
                .thenReturn(true);

        Boolean result = userService.existByUserId("key123");

        assertTrue(result);

        System.out.println(result);
    }
}
