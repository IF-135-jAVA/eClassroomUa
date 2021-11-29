package com.softserve.betterlearningroom.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Task extends Assignment {

    @Column(length = 1000)
    private String task;

    @Column(length = 6000)
    private String answer;

}