package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dto.AnswerDto;
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

    public long create(AnswerDto answerDto) {
        return answerDao.create(answerMapper.answerDtoToAnswer(answerDto));
    }

    public AnswerDto readById(long id) {
        List<Answer> result = answerDao.readById(id);
        return result.isEmpty() ? null : answerMapper.answerToAnswerDto(result.get(0));
    }

    public void update(AnswerDto answerDto, long id) {
        AnswerDto oldAnswerDto = readById(id);
        if(oldAnswerDto != null) {
            oldAnswerDto.setText(answerDto.getText());
            answerDao.update(answerMapper.answerDtoToAnswer(oldAnswerDto));
        }
    }

    public List<AnswerDto> getByUserAssignment(long userAssignmentId) {
        return answerDao.getByUserAssignment(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDto)
                .collect(Collectors.toList());
    }
}
