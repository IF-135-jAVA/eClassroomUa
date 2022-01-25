package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private Long userAssignmentId;
    private String text;
    private boolean enabled;
}
