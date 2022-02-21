package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAssignmentEvaluationDTO {
    private int grade;
    private String feedback;
}
