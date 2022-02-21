package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.LevelDTO;

import java.util.List;

public interface LevelService {

    LevelDTO findById(Long id);

    void delete(Long id);

    List<LevelDTO> findAll();

    LevelDTO save(LevelDTO levelDTO);

    LevelDTO update(Long levelId, LevelDTO levelDTO);

    List<LevelDTO> findAllByCriterionId(Long criterionId);

}
