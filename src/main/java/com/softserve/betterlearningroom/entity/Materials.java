package com.softserwe.betterlearningroom.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "materials_table")
@Data
@NoArgsConstructor
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private Calendar startData;

    @Column
    private Calendar dueDate;

    @Column
    private String formURL;

    @Column
    private String task;

    @Column
    private String answer;

}
