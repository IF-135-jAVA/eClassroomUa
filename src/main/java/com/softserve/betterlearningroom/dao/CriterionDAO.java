package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Criterion;

import java.util.List;

public interface CriterionDAO {

    Criterion save(Criterion criterion);

    Criterion update(Criterion criterion);

    List<Criterion> findAll();

    Criterion findById (Long id);

    void delete(Long id);

    List<Criterion> findAllByMaterialId(Long id);
}
