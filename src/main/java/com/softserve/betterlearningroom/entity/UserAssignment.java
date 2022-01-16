package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
