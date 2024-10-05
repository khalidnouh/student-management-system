package com.df.student.management.service;


import com.df.student.management.model.User;
import com.df.student.management.model.dto.UserDTO;
import com.df.student.management.request.UserRegisterRequest;

public interface UserService {
    UserDTO registerUser(UserRegisterRequest userRegisterRequest);
}