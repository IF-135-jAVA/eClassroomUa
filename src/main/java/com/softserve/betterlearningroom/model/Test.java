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
@DiscriminatorValue("TEST")
public class Test extends Assignment {

    @Column(length = 500)
    private String url;

}