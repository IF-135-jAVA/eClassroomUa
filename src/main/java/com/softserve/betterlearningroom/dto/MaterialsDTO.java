package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Contains data for assignment.
 *
 * @author  Igor Tryniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialsDTO {
    /**
     * Material identifier.
     */

    private Integer id;
    /**
     * Name of material.
     */
    private String title;
    /**
     * Filling material.
     */
    private String text;

    private Date startDate;

    private Date dueDate;

    private String task;

    private String answer;
    /**
     * List of urls address.
     */
    private List<String> urls;


}
