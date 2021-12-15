package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains criterion for assignment.
 *
 * @author Igor Tryniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterionDTO {
    /**
     * Criterion identifier.
     */
    private Integer id;
    /**
     * Name of criterion.
     */
    private String title;
    /**
     * Description of criterion.
     */
    private String description;
    /**
     * Material indetifier.
     */
    private Integer materialIdDTO;


}
