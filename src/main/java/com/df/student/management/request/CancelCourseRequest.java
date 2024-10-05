package com.df.student.management.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelCourseRequest {

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

}
