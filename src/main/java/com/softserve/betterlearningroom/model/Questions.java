package com.softserve.betterlearningroom.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("QUESTIONS")
public class Questions extends Assignment {

    @ElementCollection
    private List<String> questions;

    @ElementCollection
    private List<String> answers;

}
