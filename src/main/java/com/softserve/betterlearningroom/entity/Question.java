package com.softserve.betterlearningroom.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Question {

    private Long id;

    private String question;
}
