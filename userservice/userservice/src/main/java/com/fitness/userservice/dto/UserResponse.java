package com.fitness.userservice.dto;


import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data

public class UserResponse {


    private String id;
    private String keycloakId;
    private String email;

    @Column(length = 6)
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}