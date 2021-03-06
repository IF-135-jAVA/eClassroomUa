package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.CriterionDTO;

import java.util.List;

public interface CriterionServise {

     CriterionDTO findById(Long id);

     void delete(Long id);

    List<CriterionDTO> findAll();

    CriterionDTO save(CriterionDTO criterionDTO);

    CriterionDTO update(Long criterionId, CriterionDTO criterionDTO);

    List<CriterionDTO> findAllByMaterialId(Long materialId);
}
