package com.df.student.management.service;

import com.df.student.management.model.dto.CourseDTO;
import com.df.student.management.model.dto.RegistrationDTO;
import com.df.student.management.response.ApiResponse;

import java.util.List;

public interface CourseService {
    ApiResponse<List<CourseDTO>> getAllCourses();

    ApiResponse<String> addCourses(List<CourseDTO> courses);

    ApiResponse<CourseDTO> addCourse(CourseDTO courseDTO);

    ApiResponse<RegistrationDTO> registerForCourse(Long courseId);

    ApiResponse<?> cancelRegistration(Long courseId);

    byte[] getCourseScheduleAsPdf();
}
