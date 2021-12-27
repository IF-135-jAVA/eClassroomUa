package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dto.AnswerDTO;
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

    public AnswerDTO create(AnswerDTO answerDTO) {
        return answerMapper.answerToAnswerDTO(
                answerDao.create(answerMapper.answerDTOToAnswer(answerDTO)));
    }

    public AnswerDTO readById(long id) {
        return answerMapper.answerToAnswerDTO(answerDao.readById(id));
    }

    public AnswerDTO update(AnswerDTO answerDTO, long id) {
        AnswerDTO oldAnswerDTO = readById(id);
        oldAnswerDTO.setText(answerDTO.getText());
        return answerMapper.answerToAnswerDTO(
                answerDao.update(answerMapper.answerDTOToAnswer(oldAnswerDTO)));
    }

    public List<AnswerDTO> getByUserAssignment(long userAssignmentId) {
        return answerDao.getByUserAssignment(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDTO)
                .collect(Collectors.toList());
    }
}
