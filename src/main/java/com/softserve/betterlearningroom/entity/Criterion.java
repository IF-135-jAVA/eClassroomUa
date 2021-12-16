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

<<<<<<< HEAD
    private int criterionid;
=======
    private Long id;
>>>>>>> 278296fff6ff9a91a1ff442e8335e47e0c528f7d

    private String title;

    private String description;

    private Integer materialid;

    private List<Level> levels;
   
}
