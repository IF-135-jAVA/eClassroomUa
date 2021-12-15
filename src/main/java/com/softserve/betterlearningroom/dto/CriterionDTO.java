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
     * Name of material.
     */
    private String title;
    /**
     * Description of criterion.
     */
    private String description;
    /**
     * List of levels.
     */


}
