package com.softserve.betterlearningroom.entity;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Level {

    private Long id;

    private String title;

    private String description;

    private Integer mark;
}
