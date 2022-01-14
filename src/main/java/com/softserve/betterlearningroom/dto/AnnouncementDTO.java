package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private long id;
    private long courseId;
    private String text;
    private List<Comment> comments;
    private boolean enabled;
}
