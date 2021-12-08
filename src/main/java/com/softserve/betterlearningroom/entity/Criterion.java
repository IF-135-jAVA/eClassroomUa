package com.softserve.betterlearningroom.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Criterion {

    private Long id;

    private String title;

    private String description;

    private List<Level> levels;
}
