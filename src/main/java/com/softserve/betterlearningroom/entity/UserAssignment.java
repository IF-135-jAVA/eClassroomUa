package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAssignment {
    private Long id;
    private Long materialId;
    private String materialTitle;
    private LocalDateTime dueDate;
    private int maxScore;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long assignmentStatusId;
    private String assignmentStatusTitle;
    private LocalDateTime submissionDate;
    private int grade;
    private String feedback;
    private boolean enabled;
}
