package com.softserve.betterlearningroom.dto;

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

    private User author;

    private String text;

    private LocalDateTime date;

}
