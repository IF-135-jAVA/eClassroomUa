package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.AnswerDto;
import com.softserve.betterlearningroom.entity.Answer;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper {
    Answer answerDtoToAnswer(AnswerDto answerDto);
    AnswerDto answerToAnswerDto(Answer answer);
}
