package com.df.student.management.request;

import lombok.Data;
import jakarta.validation.constraints.*; // If using Jakarta

@Data
public class UserRegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

}
