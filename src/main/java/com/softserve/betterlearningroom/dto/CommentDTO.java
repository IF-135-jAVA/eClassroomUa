package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Assignment;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.User;
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
    private User author_id;
    private Announcement announcement_id;
    private Assignment user_assignment_id;
    private Material material_id;

}
