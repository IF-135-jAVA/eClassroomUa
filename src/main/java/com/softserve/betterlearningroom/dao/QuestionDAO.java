package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.Question;

import java.util.List;

public interface QuestionDAO {
    
    List<Question> findAllByMaterialId(Long materialId);
    
    List<Question> findAllByMaterial(Material material);
    
    int save(Question question, Long materialId);
    
    int update(Question question);
    
    int delete(Long questionId);
}
