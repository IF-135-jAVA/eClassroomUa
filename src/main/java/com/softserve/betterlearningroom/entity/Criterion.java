package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Criterion {

    private Long criterionId;

    private String title;

    private String description;

    private Long materialId;

    private List<Level> levels;
   
}
