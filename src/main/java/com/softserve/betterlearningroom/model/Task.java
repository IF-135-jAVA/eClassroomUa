package com.softserve.betterlearningroom.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("TASK")
public class Task extends Assignment {

    @Column(length = 1000)
    private String task;

    @Column(length = 6000)
    private String answer;

}