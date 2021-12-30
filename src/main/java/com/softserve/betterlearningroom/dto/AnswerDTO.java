package com.softserve.betterlearningroom.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private long id;
    private long userAssignmentId;
    private String text;
    private boolean enabled;
}
