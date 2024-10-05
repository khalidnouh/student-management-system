package com.df.student.management.util;

import com.df.student.management.model.User;
import com.df.student.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {
    @Autowired
    private UserRepository userRepository;
    // Helper method to get the logged-in user's username
    public String getLoggedInUser() {
        // Logic to extract username from SecurityContext or JWT
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getLoggedInUserObj() {
        // Logic to extract User from SecurityContext or JWT
       return userRepository.findByUsername(getLoggedInUser()).get();
    }
}
