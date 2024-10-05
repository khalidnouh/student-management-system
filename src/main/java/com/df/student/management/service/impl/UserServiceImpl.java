package com.df.student.management.service.impl;

import com.df.student.management.exception.UsernameAlreadyExistsException;
import com.df.student.management.model.User;
import com.df.student.management.model.dto.UserDTO;
import com.df.student.management.model.mapper.UserMapper;
import com.df.student.management.repository.UserRepository;
import com.df.student.management.request.UserRegisterRequest;
import com.df.student.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Add this if you are hashing passwords
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserRepository userRepository; // Injecting the repository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // For password hashing

    // Method to register a user
    @Override
    public UserDTO registerUser(UserRegisterRequest userRegisterRequest) {
        logger.info("entered method registerUser(Controller)," + userRegisterRequest);
        // Check if the username already exists
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        User user = new User(); // Create a new user object
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword())); // Hash the password
        user.setEmail(userRegisterRequest.getEmail());
        user= userRepository.save(user); // Save the user in the database
        return UserMapper.INSTANCE.toDTO(user);
    }
}
