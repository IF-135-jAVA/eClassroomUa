package com.softserve.betterlearningroom.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAssignment {
    private long id;
    private long materialId;
    private long userId;
    private long assignmentStatusId;
    private LocalDateTime submissionDate;
    private int grade;
    private String feedback;
    private boolean enabled;
}
