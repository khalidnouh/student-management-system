package com.df.student.management.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {

    private Long id;

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Instructor name is required")
    private String instructorName;

    private String description;
}
