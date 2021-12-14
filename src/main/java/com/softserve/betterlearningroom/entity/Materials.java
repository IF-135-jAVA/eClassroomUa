package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Materials {

    private Integer id;

    private String title;

    private String text;

    private Date startDate;

    private Date dueDate;

    private String formURL;

    private String task;

    private String answer;

}
