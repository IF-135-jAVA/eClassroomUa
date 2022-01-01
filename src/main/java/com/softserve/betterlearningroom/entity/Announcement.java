package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private long id;
    private long courseId;
    private String text;
    private List<Comment> comments;
    private boolean enabled;

    public Announcement(long id, long courseId, String text, boolean enabled) {
        this.id = id;
        this.courseId = courseId;
        this.text = text;
        this.enabled = enabled;
    }
}
