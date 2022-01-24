package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Level;

import java.util.List;

public interface LevelDao {

    Level save(Level level);

    Level update(Level level);

    List<Level> findAll();

    Level findById(Long id);

    void delete(Long id);

    List<Level> findAllByCriterionId(Long criterionId);

}
