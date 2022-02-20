package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.AnswerFileDTO;
import com.softserve.betterlearningroom.entity.AnswerFile;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerFileMapper {
    AnswerFile answerFileDTOToAnswerFile(AnswerFileDTO answerFileDTO);
    AnswerFileDTO answerFileToAnswerFileDTO(AnswerFile answerFile);
}
