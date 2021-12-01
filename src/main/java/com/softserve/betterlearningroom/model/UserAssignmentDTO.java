package com.softserve.betterlearningroom.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAssignmentDTO {
    private long id;
    private Assignment assignment;
    private User student;
    private Status assignmentStatus;
    private LocalDateTime submissionDate;
    private int grade;
    private String feedback;
}
