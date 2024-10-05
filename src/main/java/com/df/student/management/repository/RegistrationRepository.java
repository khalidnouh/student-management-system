package com.df.student.management.repository;

import com.df.student.management.model.Course;
import com.df.student.management.model.Registration;
import com.df.student.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByUserAndCourse(User user, Course course);
    List<Registration> findByUserAndCanceled(User user,Boolean cancelled);
    boolean existsByUserAndCourseId(User user, Long courseId);
    Optional<Registration> findByUserAndCourseId(User user, Long courseId);
    Optional<Registration> findByUserAndCourseIdAndCanceled(User user, Long courseId,Boolean cancelled);
}
