package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.AnswerDAO;
import com.softserve.betterlearningroom.dao.MaterialDAO;
import com.softserve.betterlearningroom.dao.UserAssignmentDAO;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.AssignmentStatus;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import com.softserve.betterlearningroom.mapper.UserAssignmentMapper;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAssignmentServiceImpl implements UserAssignmentService {
    private final UserAssignmentDAO userAssignmentDao;
    private final AnswerDAO answerDao;
    private final MaterialDAO materialDao;
    private UserAssignmentMapper userAssignmentMapper = Mappers.getMapper(UserAssignmentMapper.class);

    @Override
    public UserAssignmentDTO save(UserAssignmentDTO userAssignmentDTO) {
        Material material = materialDao.findById(userAssignmentDTO.getMaterialId());
        LocalDateTime dueDate = material.getDueDate();
        if (dueDate != null && LocalDateTime.now().isAfter(dueDate)) {
            throw new SubmissionNotAllowedException("Due date for assignment with id - " + material.getId() + " has passed. Due date is " + dueDate + ".");
        }
        userAssignmentDTO.setAssignmentStatusId(AssignmentStatus.IN_PROGRESS.getId());
        userAssignmentDTO.setSubmissionDate(null);
        userAssignmentDTO.setGrade(0);
        userAssignmentDTO.setFeedback(null);
        userAssignmentDTO.setEnabled(true);
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.save(userAssignmentMapper.userAssignmentDTOToUserAssignment(userAssignmentDTO)));
    }

    @Override
    public UserAssignmentDTO findById(Long id) {
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(userAssignmentDao.findById(id));
    }

    @Override
    public UserAssignmentDTO updateAsTeacher(UserAssignmentDTO userAssignmentDTO, Long id) {
        UserAssignmentDTO oldUserAssignmentDTO = findById(id);
        oldUserAssignmentDTO.setAssignmentStatusId(AssignmentStatus.REVIEWED.getId());
        oldUserAssignmentDTO.setGrade(userAssignmentDTO.getGrade());
        oldUserAssignmentDTO.setFeedback(userAssignmentDTO.getFeedback());
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.update(userAssignmentMapper.userAssignmentDTOToUserAssignment(oldUserAssignmentDTO)));
    }

    @Override
    public UserAssignmentDTO updateAsStudent(UserAssignmentDTO userAssignmentDTO, Long id) {
        UserAssignmentDTO oldUserAssignmentDTO = findById(id);
        if (!userAssignmentDTO.getAssignmentStatusId().equals(AssignmentStatus.REVIEWED.getId())) {
            oldUserAssignmentDTO.setAssignmentStatusId(userAssignmentDTO.getAssignmentStatusId());
        }
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.update(userAssignmentMapper.userAssignmentDTOToUserAssignment(oldUserAssignmentDTO)));
    }

    @Override
    public void delete(Long id) {
        answerDao.findByUserAssignmentId(id)
                .stream()
                .map(Answer::getId)
                .forEach(answerDao::delete);
        userAssignmentDao.delete(id);
    }

    @Override
    @PostFilter("hasRole('TEACHER') or filterObject.userId.equals(authentication.principal.getId())")
    public List<UserAssignmentDTO> findByAssignmentId(Long assignmentId) {
        return userAssignmentDao.findByAssignmentId(assignmentId)
                .stream()
                .map(userAssignmentMapper::userAssignmentToUserAssignmentDTO)
                .collect(Collectors.toList());
    }
}
