package com.df.student.management.service;


import com.df.student.management.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(String username, String password);
}