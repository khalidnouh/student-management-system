//package com.df.student.management.controller;
//import com.df.student.management.model.Registration;
//import com.df.student.management.request.CancelCourseRequest;
//import com.df.student.management.request.RegisterCourseRequest;
//import com.df.student.management.response.ApiResponse;
//import com.df.student.management.service.RegistrationService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/courses")
//public class RegistrationController {
//
//    @Autowired
//    private RegistrationService registrationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse<Registration>> registerCourse(@Valid @RequestBody RegisterCourseRequest request) {
//      return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.registerForCourse(request.getCourseId()));
//    }
//
//    @DeleteMapping("/cancel")
//    public ResponseEntity<ApiResponse<String>> cancelCourse(@Valid @RequestBody CancelCourseRequest request) {
//        String username = getLoggedInUser();
//        registrationService.cancelRegistration(username, request.getCourseId());
//        ApiResponse<String> response = new ApiResponse<>("cancelled", "Course registration cancelled successfully", HttpStatus.NO_CONTENT);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
//    }
//
//    // Helper method to get the logged-in user's username
//    private String getLoggedInUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName(); // Fetches the username from the SecurityContext
//    }
//}
