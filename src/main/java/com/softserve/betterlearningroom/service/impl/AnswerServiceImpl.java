package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnswerDAO;
import com.softserve.betterlearningroom.dao.UserAssignmentDAO;
import com.softserve.betterlearningroom.dto.AnswerDTO;
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
    private final AnswerDAO answerDao;
    private final UserAssignmentDAO userAssignmentDao;
    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    @Override
    public AnswerDTO save(AnswerDTO answerDTO) {
        UserAssignment userAssignment;
        try {
            userAssignment = userAssignmentDao.findById(answerDTO.getUserAssignmentId());
        } catch (DataRetrievalFailureException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        checkIfSubmissionAllowed(userAssignment);
        answerDTO.setEnabled(true);
        AnswerDTO result = answerMapper.answerToAnswerDTO(answerDao.save(answerMapper.answerDTOToAnswer(answerDTO)));
        renewUserAssignmentSubmissionDate(userAssignment);
        return result;
    }

    @Override
    public AnswerDTO findById(Long id) {
        return answerMapper.answerToAnswerDTO(answerDao.findById(id));
    }

    @Override
    public AnswerDTO update(AnswerDTO answerDTO, Long id) {
        AnswerDTO oldAnswerDTO = findById(id);
        UserAssignment userAssignment = userAssignmentDao.findById(oldAnswerDTO.getUserAssignmentId());
        checkIfSubmissionAllowed(userAssignment);
        oldAnswerDTO.setText(answerDTO.getText());
        AnswerDTO result = answerMapper.answerToAnswerDTO(answerDao.update(answerMapper.answerDTOToAnswer(oldAnswerDTO)));
        renewUserAssignmentSubmissionDate(userAssignment);
        return result;
    }

    @Override
    public void delete(Long id) {
        UserAssignment userAssignment = userAssignmentDao.findById(findById(id).getUserAssignmentId());
        checkIfSubmissionAllowed(userAssignment);
        answerDao.delete(id);
        renewUserAssignmentSubmissionDate(userAssignment);
    }

    @Override
    public List<AnswerDTO> findByUserAssignmentId(Long userAssignmentId) {
        return answerDao.findByUserAssignmentId(userAssignmentId)
                .stream()
                .map(answerMapper::answerToAnswerDTO)
                .collect(Collectors.toList());
    }

    private void renewUserAssignmentSubmissionDate(UserAssignment userAssignment) {
        userAssignment.setSubmissionDate(LocalDateTime.now());
        userAssignmentDao.update(userAssignment);
    }

    private void checkIfSubmissionAllowed(UserAssignment userAssignment) {
        LocalDateTime dueDate = userAssignment.getDueDate();
        if (dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            throw new SubmissionNotAllowedException("Due date for assignment with id - " + userAssignment.getMaterialId() + " has passed. Due date is " + dueDate + ".");
        }
    }
}
