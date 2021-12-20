package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.*;
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
    private long author_id;
    private long announcement_id;
    private long user_assignment_id;
    private long material_id;

}
