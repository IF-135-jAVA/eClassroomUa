package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Answer;

import java.util.List;

public interface AnswerDAO {

    Answer save(Answer answer);

    Answer findById(Long id);

    Answer update(Answer answer);

    void delete(Long id);

    List<Answer> findByUserAssignmentId(Long userAssignmentId);
}
