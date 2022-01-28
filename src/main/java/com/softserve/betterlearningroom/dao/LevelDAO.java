package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Level;

import java.util.List;

public interface LevelDAO {

    Level save(Level level);

    Level update(Level level);

    List<Level> findAll();

    Level findById(Long id);

    void removeById(Long id);

    List<Level> findAllByCriterionId(Long criterionId);

}
