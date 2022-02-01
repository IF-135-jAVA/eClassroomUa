package com.softserve.betterlearningroom.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Link {

    private Long id;

    private String url;

    private String text;
}
