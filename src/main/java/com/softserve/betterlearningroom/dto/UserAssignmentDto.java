package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Assignment;
import com.softserve.betterlearningroom.entity.Status;
import com.softserve.betterlearningroom.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAssignmentDto {
    private long id;
    private Assignment assignment;
    private User student;
    private Status assignmentStatus;
    private LocalDateTime submissionDate;
    private int grade;
    private String feedback;
}
