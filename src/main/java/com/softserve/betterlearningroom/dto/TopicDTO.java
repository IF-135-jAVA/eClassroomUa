package com.softserwe.betterlearningroom.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TopicDTO {
    /**
     * Topic identifier.
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
    private List<ClassroomDTO> classroomDTOList;
    /**
     * List of materials.
     */
    private List<MaterialsDTO> materialsDTO;
}
