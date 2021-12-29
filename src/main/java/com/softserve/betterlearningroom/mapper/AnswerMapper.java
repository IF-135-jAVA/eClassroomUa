package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.entity.Answer;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper {
    Answer answerDTOToAnswer(AnswerDTO answerDTO);
    AnswerDTO answerToAnswerDTO(Answer answer);
}
