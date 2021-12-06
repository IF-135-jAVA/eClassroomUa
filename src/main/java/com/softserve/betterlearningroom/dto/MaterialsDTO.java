package com.softserwe.betterlearningroom.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Contains data for assignment.
 *
 * @author  Igor Tryniak
 */
public class MaterialsDTO {
    /**
     * Material identifier.
     */
    @NotNull
    private int id;
    /**
     * Name of material.
     */
    private String title;
    /**
     * Filling material.
     */
    private String text;
    /**
     * List of urls address.
     */
    private List<String> urls;

}
