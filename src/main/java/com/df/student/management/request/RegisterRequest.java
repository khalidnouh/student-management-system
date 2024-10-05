package com.df.student.management.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegisterRequest {
    private Long courseId;
    private LocalDateTime scheduleStartTime; // Add schedule start time
    private LocalDateTime scheduleEndTime;   // Add schedule end time
    private String scheduleLocation;           // Add schedule location
}