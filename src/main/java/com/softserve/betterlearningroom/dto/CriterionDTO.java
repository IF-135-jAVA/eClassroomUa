package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Contains criterion for assignment.
 *
 * @author  Igor Tryniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriterionDTO {
    /**
     * Criterion identifier.
     */
    @NotNull
    private Integer id;
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
    private List<LevelDTO> levels;

}
