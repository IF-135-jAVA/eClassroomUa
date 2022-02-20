package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private long id;
    private String courseId;
    private String text;
    private List<Comment> comments;
    private boolean enabled;
}
