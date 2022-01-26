package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Answer;

import java.util.List;

public interface AnswerDao {

    Answer create(Answer answer);

    Answer readById(long id);

    Answer update(Answer answer);

    void delete(long id);

    List<Answer> getByUserAssignment(long userAssignmentId);
}
