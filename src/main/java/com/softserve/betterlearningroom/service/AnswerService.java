package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    AnswerDTO create(AnswerDTO answerDTO);

    AnswerDTO readById(long id);

    AnswerDTO update(AnswerDTO answerDTO, long id);

    void delete(long id);

    List<AnswerDTO> getByUserAssignment(long userAssignmentId);
}
