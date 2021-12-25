package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerDao answerDao;

    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    public long create(AnswerDTO answerDto) {
        return answerDao.create(answerMapper.answerDtoToAnswer(answerDto));
    }

    public AnswerDTO readById(long id) {
        List<Answer> result = answerDao.readById(id);
        return result.isEmpty() ? null : answerMapper.answerToAnswerDto(result.get(0));
    }

    public void update(AnswerDTO answerDto, long id) {
        AnswerDTO oldAnswerDTO = readById(id);
        if(oldAnswerDTO != null) {
            oldAnswerDTO.setText(answerDto.getText());
            answerDao.update(answerMapper.answerDtoToAnswer(oldAnswerDTO));
        }
    }

    public List<AnswerDTO> getByUserAssignment(long userAssignmentId) {
        return answerDao.getByUserAssignment(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDto)
                .collect(Collectors.toList());
    }
}
