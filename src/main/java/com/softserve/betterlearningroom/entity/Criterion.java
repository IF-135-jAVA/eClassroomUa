package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "criterion_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Integer materialId;

    @OneToMany
    private List<Level> level;


}
