package com.df.student.management.controller;


import com.df.student.management.request.AuthRequest;
import com.df.student.management.response.ApiResponse;
import com.df.student.management.response.AuthResponseDTO;
import com.df.student.management.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;


@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class.getName());
    @Autowired
    private AuthServiceImpl authServiceImpl; // Service for handling authentication logic

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponseDTO token = authServiceImpl.login(authRequest.getUsername(), authRequest.getPassword());
        ApiResponse<AuthResponseDTO> response = new ApiResponse<>(token, "Login successful", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

}

