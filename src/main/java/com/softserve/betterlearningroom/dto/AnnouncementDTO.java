package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private long id;
    private long courseId;
    private String text;
    private List<Comment> comments;
    private boolean enabled;

    public AnnouncementDTO(long id, long courseId, String text, boolean enabled) {
        this.id = id;
        this.courseId = courseId;
        this.text = text;
        this.enabled = enabled;
    }
}
