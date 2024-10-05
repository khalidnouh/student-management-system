package com.df.student.management.service.impl;

import com.df.student.management.model.Course;
import com.df.student.management.model.Registration;
import com.df.student.management.model.User;
import com.df.student.management.model.dto.CourseDTO;
import com.df.student.management.model.dto.RegistrationDTO;
import com.df.student.management.model.mapper.CourseMapper;
import com.df.student.management.model.mapper.RegistrationMapper;
import com.df.student.management.repository.CourseRepository;
import com.df.student.management.repository.RegistrationRepository;
import com.df.student.management.response.ApiResponse;
import com.df.student.management.service.CourseService;
import com.df.student.management.util.PdfUtil;
import com.df.student.management.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class.getName());
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private PdfUtil pdfUtil;

    // View all available courses
    @Override
    @Cacheable(value = "courses")
    public ApiResponse<List<CourseDTO>> getAllCourses() {
        logger.info("entered method getAllCourses");
        ApiResponse<List<CourseDTO>> response = new ApiResponse<>(courseRepository.findAll().stream().map(CourseMapper.INSTANCE::toDTO).toList(), null, HttpStatus.OK);
        return response;
    }

    //add a course
    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public ApiResponse<String> addCourses(List<CourseDTO> courses) {
        logger.info("entered method addCourses");
        courses.forEach(course -> {
            addCourse(course);
        });
        ApiResponse<String> response = new ApiResponse<>("Success", "Course added successfully", HttpStatus.CREATED);
        return response;
    }

    //add a course
    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public ApiResponse<CourseDTO> addCourse(CourseDTO courseDTO) {
        logger.info("entered method addCourses");
        Course course=new Course();
        BeanUtils.copyProperties(courseDTO,course,"id");
        course = courseRepository.save(course);
        ApiResponse<CourseDTO> response = new ApiResponse<>(CourseMapper.INSTANCE.toDTO(course), "Course added successfully", HttpStatus.CREATED);
        return response;
    }

    // Register for a course
    @Override
    public ApiResponse<RegistrationDTO> registerForCourse(Long courseId) {
        logger.info("entered method registerForCourse,{}", courseId);
        // Check if the user is already registered
        User user = securityUtil.getLoggedInUserObj();
        if (registrationRepository.existsByUserAndCourseId(user, courseId)) {
            throw new RuntimeException("User is already registered for the course");
        }

        // Register user for the course
        Registration registration = new Registration();
        registration.setUser(user);
        Optional<Course> course = courseRepository.findById(courseId);
        registration.setCourse(course.get());
        registration=registrationRepository.save(registration);
        ApiResponse<RegistrationDTO> response = new ApiResponse<>(RegistrationMapper.INSTANCE.toDTO(registration), "Course registered successfully", HttpStatus.CREATED);
        return response;
    }

    // Cancel course registration
    @Override
    public ApiResponse<RegistrationDTO> cancelRegistration(Long courseId) {
        logger.info("entered method cancelRegistration," + courseId);
        // Check if the user is registered
        User user = securityUtil.getLoggedInUserObj();
        Registration registration = registrationRepository.findByUserAndCourseIdAndCanceled(user, courseId, false)
                .orElseThrow(() -> new RuntimeException("Course Not Registred Or Maybe it's Already Cancelled "));

        // Cancel the registration
        registration.setCanceled(true);
        registration = registrationRepository.save(registration);
        ApiResponse<RegistrationDTO> response = new ApiResponse<>(RegistrationMapper.INSTANCE.toDTO(registration), "Course cancelled successfully", HttpStatus.OK);
        logger.info("exiting method cancelRegistration," + response);
        return response;
    }

    // Generate course schedule as PDF
    @Override
    public byte[] getCourseScheduleAsPdf() {
        logger.info("entered method getCourseScheduleAsPdf,");
        // Retrieve registered courses for the user
        User user = securityUtil.getLoggedInUserObj();
        List<Registration> registeredCourses = registrationRepository.findByUserAndCanceled(user,false);

        // Logic to generate a PDF from the course data (using a library like iText)
        return pdfUtil.generatePdf(registeredCourses);
    }
}
