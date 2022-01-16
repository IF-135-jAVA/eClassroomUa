package com.softserve.betterlearningroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDTO {
    /**
     * Topic identifier.
     */
    private Long id;
    /**
     * Name of material.
     */
    private String title;
    /**
     * List of classrooms.
     */
    private Long classroomId;

}
