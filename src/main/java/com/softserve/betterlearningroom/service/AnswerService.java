package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    AnswerDTO save(AnswerDTO answerDTO);

    AnswerDTO findById(Long id);

    AnswerDTO update(AnswerDTO answerDTO, Long id);

    void delete(Long id);

    List<AnswerDTO> findByUserAssignmentId(Long userAssignmentId);
}
