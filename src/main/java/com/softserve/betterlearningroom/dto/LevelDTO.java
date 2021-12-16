package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains data for level.
 *
 * @author  Igor Tryniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelDTO {
    /**
     * Level identifier.
     */
    private Integer id;
    /**
     * Name of level.
     */
    private String title;
    /**
     * Description of level.
     */
    private String description;
    /**
     * Criterion identifier.
     */
    private Integer criterionId;
    /**
     * Mark for level.
     */
    private Integer mark;
}
