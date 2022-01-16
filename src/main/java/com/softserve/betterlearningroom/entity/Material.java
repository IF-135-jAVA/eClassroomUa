package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Material {

    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    private MaterialType materialType;

    @EqualsAndHashCode.Include
    private String title;

    private String text;

    private List<Link> urls;

    private Long topicId;

    private Long classroomId;

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    private List<Criterion> criterions;

    private List<Question> questions;

    private List<User> students;

    private int maxScore;

    private String task;

    private String answer;

    private String url;

}
