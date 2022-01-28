package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialsDTO {
    private Integer id;
    private String title;
    private String text;
    private Date startDate;
    private Date dueDate;
    private String task;
    private String answer;
    private List<String> urls;
}
