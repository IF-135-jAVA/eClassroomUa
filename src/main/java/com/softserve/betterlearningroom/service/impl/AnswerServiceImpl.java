package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.UserAssignment;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.mapper.AnswerMapper;
import com.softserve.betterlearningroom.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDao answerDao;
    private final UserAssignmentDao userAssignmentDao;
    private final MaterialDao materialDao;

    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    @Override
    public AnswerDTO create(AnswerDTO answerDTO) {
        UserAssignment userAssignment;
        try {
            // throws an exception if UserAssignment with the specified id is absent or disabled (deleted)
            userAssignment = userAssignmentDao.readById(answerDTO.getUserAssignmentId());
        } catch (DataRetrievalFailureException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        checkIfSubmissionAllowed(userAssignment); // throws an exception if due date for assignment has passed
        answerDTO.setEnabled(true);
        AnswerDTO result = answerMapper.answerToAnswerDTO(answerDao.create(answerMapper.answerDTOToAnswer(answerDTO)));
        renewUserAssignmentSubmissionDate(userAssignment);
        return result;
    }

    @Override
    public AnswerDTO readById(long id) {
        return answerMapper.answerToAnswerDTO(answerDao.readById(id));
    }

    @Override
    public AnswerDTO update(AnswerDTO answerDTO, long id) {
        AnswerDTO oldAnswerDTO = readById(id);
        UserAssignment userAssignment = userAssignmentDao.readById(oldAnswerDTO.getUserAssignmentId());
        checkIfSubmissionAllowed(userAssignment);
        oldAnswerDTO.setText(answerDTO.getText());
        AnswerDTO result = answerMapper.answerToAnswerDTO(answerDao.update(answerMapper.answerDTOToAnswer(oldAnswerDTO)));
        renewUserAssignmentSubmissionDate(userAssignment);
        return result;
    }

    @Override
    public void delete(long id) {
        UserAssignment userAssignment = userAssignmentDao.readById(readById(id).getUserAssignmentId());
        checkIfSubmissionAllowed(userAssignment);
        answerDao.delete(id);
        renewUserAssignmentSubmissionDate(userAssignment);
    }

    @Override
    public List<AnswerDTO> getByUserAssignment(long userAssignmentId) {
        return answerDao.getByUserAssignment(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDTO)
                .collect(Collectors.toList());
    }

    private void renewUserAssignmentSubmissionDate(UserAssignment userAssignment) {
        userAssignment.setSubmissionDate(LocalDateTime.now());
        userAssignmentDao.update(userAssignment);
    }

    private void checkIfSubmissionAllowed(UserAssignment userAssignment) {
        Material material = materialDao.readById(userAssignment.getMaterialId());
        LocalDateTime dueDate = material.getDueDate();
        if (dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            throw new SubmissionNotAllowedException("Due date for assignment with id - " + material.getId() + " has passed. Due date is " + dueDate + ".");
        }
    }
}
