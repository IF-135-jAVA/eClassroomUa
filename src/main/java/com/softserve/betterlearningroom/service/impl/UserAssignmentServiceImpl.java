package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.AssignmentStatus;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.mapper.UserAssignmentMapper;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAssignmentServiceImpl implements UserAssignmentService {

    private final UserAssignmentDao userAssignmentDao;
    private final AnswerDao answerDao;
    private final MaterialDao materialDao;

    private UserAssignmentMapper userAssignmentMapper = Mappers.getMapper(UserAssignmentMapper.class);

    @Override
    public UserAssignmentDTO create(UserAssignmentDTO userAssignmentDTO) {
        Material material = materialDao.findById(userAssignmentDTO.getMaterialId());
        LocalDateTime dueDate = material.getDueDate();
        if (dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            throw new SubmissionNotAllowedException("Due date for assignment with id - " + material.getId() + " has passed. Due date is " + dueDate + ".");
        }
        userAssignmentDTO.setAssignmentStatusId(AssignmentStatus.TODO.getId());
        userAssignmentDTO.setSubmissionDate(null);
        userAssignmentDTO.setGrade(0);
        userAssignmentDTO.setFeedback(null);
        userAssignmentDTO.setEnabled(true);
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.save(userAssignmentMapper.userAssignmentDTOToUserAssignment(userAssignmentDTO)));
    }

    @Override
    public UserAssignmentDTO readById(long id) {
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(userAssignmentDao.findById(id));
    }

    @Override
    public UserAssignmentDTO update(UserAssignmentDTO userAssignmentDTO, long id) {
        UserAssignmentDTO oldUserAssignmentDTO = readById(id);
        oldUserAssignmentDTO.setAssignmentStatusId(userAssignmentDTO.getAssignmentStatusId());
        oldUserAssignmentDTO.setGrade(userAssignmentDTO.getGrade());
        oldUserAssignmentDTO.setFeedback(userAssignmentDTO.getFeedback());
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.update(userAssignmentMapper.userAssignmentDTOToUserAssignment(oldUserAssignmentDTO)));
    }

    @Override
    public void delete(long id) {
        answerDao.findByUserAssignmentId(id)
                .stream()
                .map(Answer::getId)
                .forEach(answerDao::delete);
        userAssignmentDao.delete(id);
    }

    @Override
    public List<UserAssignmentDTO> getByAssignment(long assignmentId) {
        return userAssignmentDao.findByAssignmentId(assignmentId)
                .stream()
                .map(userAssignmentMapper::userAssignmentToUserAssignmentDTO)
                .collect(Collectors.toList());
    }
}
