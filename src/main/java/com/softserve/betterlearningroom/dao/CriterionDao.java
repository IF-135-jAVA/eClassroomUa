package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Criterion;

import java.util.List;

public interface CriterionDao {

    Criterion save(Criterion criterion);

    Criterion update(Criterion criterion);

    List<Criterion> findAll();

    Criterion findById (Long id);

    void removeById(Long id);



}
