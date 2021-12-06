package com.softserve.betterlearningroom.dto;

import javax.validation.constraints.NotNull;

/**
 * Contains data for level.
 *
 * @author  Igor Tryniak
 */
public class Level {
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
