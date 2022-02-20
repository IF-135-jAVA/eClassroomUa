package com.softserve.betterlearningroom.dto;

import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.entity.Link;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.entity.Question;
import com.softserve.betterlearningroom.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MaterialDTO {
    private Long id;
    private MaterialType materialType;
    private String title;
    private String text;
    private List<Link> urls;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private List<Criterion> criterions;
    private List<User> students;
    private int maxScore;
    private String task;
    private String url;
    private Long topicId;
    private String classroomId;
    private List<Question> questions;
}
