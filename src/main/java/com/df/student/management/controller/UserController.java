package com.df.student.management.controller;
import com.df.student.management.model.dto.UserDTO;
import com.df.student.management.request.UserRegisterRequest;
import com.df.student.management.response.ApiResponse;
import com.df.student.management.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    @Autowired
    private UserServiceImpl userServiceImpl; // Injecting the UserService

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Long>> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserDTO user = userServiceImpl.registerUser(userRegisterRequest); // Call the service to register user
        ApiResponse<Long> response = new ApiResponse<>(user.getId(), "User registered successfully", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
