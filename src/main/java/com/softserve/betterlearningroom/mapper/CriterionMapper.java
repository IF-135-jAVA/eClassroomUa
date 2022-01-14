package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;

public class CriterionMapper {
    public static Criterion toEntity(CriterionDTO criterionDTO) {
        return Criterion.builder()
                .criterionId(criterionDTO.getId())
                .materialId(criterionDTO.getMaterialIdDTO())
                .title(criterionDTO.getTitle())
                .description(criterionDTO.getDescription())
                .build();
    }

    public static CriterionDTO toDTO(Criterion criterion) {
        return CriterionDTO.builder()
                .id(criterion.getCriterionId())
                .materialIdDTO(criterion.getMaterialId())
                .title(criterion.getTitle())
                .description(criterion.getDescription())
                .build();
    }
}
