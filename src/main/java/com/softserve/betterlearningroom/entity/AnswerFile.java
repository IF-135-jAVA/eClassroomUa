package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerFile {
    private long id;
    private long userAssignmentId;
    private String path;
    private String fileName;
}
