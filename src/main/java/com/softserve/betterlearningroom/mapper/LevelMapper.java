package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.entity.Level;

public class LevelMapper {

    public static Level toEntity(LevelDTO levelDTO) {
        return Level.builder()
                .levelId(levelDTO.getId())
                .criterionId(levelDTO.getCriterionId())
                .title(levelDTO.getTitle())
                .description(levelDTO.getDescription())
                .mark(levelDTO.getMark())
                .build();

    }

    public static LevelDTO toDTO(Level level) {
        return LevelDTO.builder()
                .id(level.getLevelId())
                .criterionId(level.getCriterionId())
                .title(level.getTitle())
                .description(level.getDescription())
                .mark(level.getMark())
                .build();
    }
}
