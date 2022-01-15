package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions(Long materialId);
    List<Question> getAllQuestions(Material material);
    int addQuestion(Question question, Long materialId);
    int addQuestion(Question question, Material material);
    int updateQuestion(Question question);
    int removeQuestion(Long questionId);
    int removeQuestion(Question question);
}
