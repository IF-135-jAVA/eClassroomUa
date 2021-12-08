package com.softserve.betterlearningroom.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Questions extends Assignment {

    private List<Question> questions;
}
