package com.df.student.management.service.impl;

import com.df.student.management.model.User;
import com.df.student.management.repository.UserRepository;
import com.df.student.management.response.AuthResponseDTO;
import com.df.student.management.service.AuthService;
import com.df.student.management.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class.getName());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO login(String username, String password) {
        // Retrieve user by username from the database
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verify the password
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate JWT token if authentication is successful
                return new AuthResponseDTO(jwtUtil.generateToken(username),username);
            } else {
                throw new RuntimeException("Invalid username or password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
