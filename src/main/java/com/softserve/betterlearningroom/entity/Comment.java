package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private String text;
    private LocalDateTime date;
    private long authorId;
    private long announcementId;
    private long userAssignmentId;
    private long materialId;
}
