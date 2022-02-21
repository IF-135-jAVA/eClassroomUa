package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.AnswerFile;

import java.util.List;

public interface AnswerFileDAO {

    AnswerFile save(AnswerFile answerFile);

    AnswerFile findById(Long id);

    List<AnswerFile> findByUserAssignmentId(Long userAssignmentId);
}
