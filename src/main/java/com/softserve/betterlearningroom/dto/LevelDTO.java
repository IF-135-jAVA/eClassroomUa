package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    @NotNull
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
     * Mark for level.
     */
    private Integer mark;
}
