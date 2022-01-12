package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnswerDao;
import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.entity.Answer;
import com.softserve.betterlearningroom.entity.AssignmentStatus;
import com.softserve.betterlearningroom.mapper.UserAssignmentMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAssignmentService {

    private final UserAssignmentDao userAssignmentDao;
    private final AnswerDao answerDao;

    private UserAssignmentMapper userAssignmentMapper = Mappers.getMapper(UserAssignmentMapper.class);

    public UserAssignmentDTO create(UserAssignmentDTO userAssignmentDTO) {
        userAssignmentDTO.setAssignmentStatusId(AssignmentStatus.TODO.getId());
        userAssignmentDTO.setSubmissionDate(LocalDateTime.now());
        userAssignmentDTO.setEnabled(true);
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.create(userAssignmentMapper.userAssignmentDTOToUserAssignment(userAssignmentDTO)));
    }

    public UserAssignmentDTO readById(long id) {
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(userAssignmentDao.readById(id));
    }

    public UserAssignmentDTO update(UserAssignmentDTO userAssignmentDTO, long id) {
        UserAssignmentDTO oldUserAssignmentDTO = readById(id);
        oldUserAssignmentDTO.setAssignmentStatusId(userAssignmentDTO.getAssignmentStatusId());
        oldUserAssignmentDTO.setGrade(userAssignmentDTO.getGrade());
        oldUserAssignmentDTO.setFeedback(userAssignmentDTO.getFeedback());
        return userAssignmentMapper.userAssignmentToUserAssignmentDTO(
                userAssignmentDao.update(userAssignmentMapper.userAssignmentDTOToUserAssignment(oldUserAssignmentDTO)));
    }

    public void delete(long id) {
        answerDao.getByUserAssignment(id)
                .stream()
                .map(Answer::getId)
                .forEach(answerDao::delete);
        userAssignmentDao.delete(id);
    }

    public List<UserAssignmentDTO> getByAssignment(long assignmentId) {
        return userAssignmentDao.getByAssignment(assignmentId)
                .stream()
                .map(userAssignmentMapper::userAssignmentToUserAssignmentDTO)
                .collect(Collectors.toList());
    }
}
