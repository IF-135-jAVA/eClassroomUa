package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private long id;
    private long userAssignmentId;
    private String text;
    private boolean enabled;
}
