package com.softserve.betterlearningroom.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Assignment extends Material {

    private LocalDateTime startDate;

    private LocalDateTime dueDate;

    @OneToMany
    private List<Criterion> criterions;

    @OneToMany
    private List<User> students;

    @Column(nullable = false)
    private int maxScore;
}
