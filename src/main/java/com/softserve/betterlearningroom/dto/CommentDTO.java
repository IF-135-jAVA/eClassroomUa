package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private long id;
    private String text;
    private LocalDateTime date;
    private Integer authorId;
    private Integer announcementId;
    private Integer userAssignmentId;
    private Integer materialId;

}
