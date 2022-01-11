package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.mapper.AnswerMapper;
import com.softserve.betterlearningroom.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;
    private final UserAssignmentDao userAssignmentDao;

    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    @Override
    public AnswerDTO create(AnswerDTO answerDTO) {
        try {
            // throws an exception if UserAssignment with the specified id is absent or disabled (deleted)
            userAssignmentDao.readById(answerDTO.getUserAssignmentId());
        } catch (DataRetrievalFailureException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        answerDTO.setEnabled(true);
        return answerMapper.answerToAnswerDTO(
                answerDao.create(answerMapper.answerDTOToAnswer(answerDTO)));
    }

    @Override
    public AnswerDTO readById(long id) {
        return answerMapper.answerToAnswerDTO(answerDao.readById(id));
    }

    @Override
    public AnswerDTO update(AnswerDTO answerDTO, long id) {
        AnswerDTO oldAnswerDTO = readById(id);
        oldAnswerDTO.setText(answerDTO.getText());
        return answerMapper.answerToAnswerDTO(
                answerDao.update(answerMapper.answerDTOToAnswer(oldAnswerDTO)));
    }

    @Override
    public void delete(long id) {
        answerDao.delete(id);
    }

    @Override
    public List<AnswerDTO> getByUserAssignment(long userAssignmentId) {
        return answerDao.getByUserAssignment(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDTO)
                .collect(Collectors.toList());
    }
}
