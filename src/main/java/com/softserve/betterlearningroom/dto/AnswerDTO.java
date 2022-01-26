package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private long id;
    private long userAssignmentId;
    private String text;
    private boolean enabled;
}
