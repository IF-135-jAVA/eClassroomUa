package com.softserve.betterlearningroom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private LocalDateTime date;
    private Long authorId;
    private Long announcementId;
    private Long userAssignmentId;
    private Long materialId;
    private boolean enabled;
}
