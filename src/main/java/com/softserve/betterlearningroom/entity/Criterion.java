package com.softserve.betterlearningroom.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "criterions_table")
@Data
@NoArgsConstructor
public class Criterions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany
    private List<Level> levels;


}
