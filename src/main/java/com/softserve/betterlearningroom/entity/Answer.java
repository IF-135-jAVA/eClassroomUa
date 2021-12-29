package com.softserve.betterlearningroom.entity;

import lombok.Data;

@Data
public class Answer {
    private long id;
    private long userAssignmentId;
    private String text;
    private boolean enabled;
}
