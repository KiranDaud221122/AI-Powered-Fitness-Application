package com.fitness.userservice.services;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            User ExistingUser = userRepository.findByEmail(request.getEmail());

            UserResponse userResponse = new UserResponse();
            userResponse.setId(ExistingUser.getId());
            userResponse.setPassword(passwordEncoder.encode(ExistingUser.getPassword()));
            userResponse.setEmail(ExistingUser.getEmail());
            userResponse.setFirstName(ExistingUser.getFirstName());
            userResponse.setLastName(ExistingUser.getLastName());
            userResponse.setCreatedAt(ExistingUser.getCreatedAt());
            userResponse.setUpdatedAt(ExistingUser.getUpdatedAt());
            return userResponse;
        }

        User user =new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setKeycloakId(request.getKeycloakId());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saveUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(saveUser.getId());
        userResponse.setPassword(saveUser.getPassword());
        userResponse.setEmail(saveUser.getEmail());
        userResponse.setFirstName(saveUser.getFirstName());
        userResponse.setLastName(saveUser.getLastName());
        userResponse.setCreatedAt(saveUser.getCreatedAt());
        userResponse.setUpdatedAt(saveUser.getUpdatedAt());
        return userResponse;
    }

    public UserResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException(" User not found"));
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
         userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;

    }

    public Boolean existByUserId(String userId) {
        log.info("User exist Checked done {} !", userId);
        return userRepository.existsByKeycloakId(userId);
    }


}
