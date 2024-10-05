package com.df.student.management.controller;

import com.df.student.management.model.dto.CourseDTO;
import com.df.student.management.model.dto.RegistrationDTO;
import com.df.student.management.response.ApiResponse;
import com.df.student.management.service.impl.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private Logger logger = LoggerFactory.getLogger(CourseController.class.getName());
    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses() {
        logger.info("entered method getAllCourses (Controller)");
        return ResponseEntity.status(HttpStatus.OK).body(courseServiceImpl.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseDTO>> add(@RequestBody CourseDTO course) {
        logger.info("entered method add (Controller),{}",course);
        return ResponseEntity.status(HttpStatus.OK).body(courseServiceImpl.addCourse(course));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<String>> addCourses(@RequestBody List<CourseDTO> courses) {
        logger.info("entered method add (Controller),{}",courses);
        return ResponseEntity.status(HttpStatus.OK).body(courseServiceImpl.addCourses(courses));
    }

    @PostMapping("/{courseId}/register")
    public ResponseEntity<ApiResponse<RegistrationDTO>> registerForCourse(@PathVariable Long courseId) {
        logger.info("entered method registerForCourse (Controller),{}",courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseServiceImpl.registerForCourse(courseId));
    }

    @PutMapping("/{courseId}/cancel")
    public ResponseEntity<ApiResponse<RegistrationDTO>> cancelCourseRegistration(@PathVariable Long courseId) {
        logger.info("entered method cancelCourseRegistration (Controller),{}",courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseServiceImpl.cancelRegistration(courseId));
    }

    @GetMapping("/schedule/pdf")
    public ResponseEntity<byte[]> getCourseScheduleAsPdf() {
        logger.info("entered method getCourseScheduleAsPdf (Controller)");
        byte[] pdfBytes = courseServiceImpl.getCourseScheduleAsPdf();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=course_schedule.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


}