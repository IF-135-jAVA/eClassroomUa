package com.softserve.betterlearningroom.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private long id;
    private long userAssignmentId;
    private String text;
}
