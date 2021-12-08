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
public class Link {

    private Long id;

    private String url;

    private String text;
}
