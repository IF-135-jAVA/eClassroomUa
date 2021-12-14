package com.softserve.betterlearningroom.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAssignmentDto {
    private long id;
    private long materialId;
    private long userId;
    private String assignmentStatus;
    private LocalDateTime submissionDate;
    private int grade;
    private String feedback;
}
