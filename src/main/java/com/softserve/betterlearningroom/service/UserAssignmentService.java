package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.dto.UserAssignmentDto;
import com.softserve.betterlearningroom.entity.UserAssignment;
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

    private UserAssignmentMapper userAssignmentMapper = Mappers.getMapper(UserAssignmentMapper.class);

    public long create(UserAssignmentDto userAssignmentDto) {
        userAssignmentDto.setSubmissionDate(LocalDateTime.now());
        return userAssignmentDao.create(userAssignmentMapper.userAssignmentDtoToUserAssignment(userAssignmentDto));
    }

    public UserAssignmentDto readById(long id) {
        List<UserAssignment> result = userAssignmentDao.readById(id);
        return result.isEmpty() ? null : userAssignmentMapper.userAssignmentToUserAssignmentDto(result.get(0));
    }

    public void update(UserAssignmentDto userAssignmentDto, long id) {
        UserAssignmentDto oldUserAssignmentDto = readById(id);
        if(oldUserAssignmentDto != null) {
            oldUserAssignmentDto.setAssignmentStatus(userAssignmentDto.getAssignmentStatus());
            oldUserAssignmentDto.setGrade(userAssignmentDto.getGrade());
            oldUserAssignmentDto.setFeedback(userAssignmentDto.getFeedback());
            userAssignmentDao.update(userAssignmentMapper.userAssignmentDtoToUserAssignment(oldUserAssignmentDto));
        }
    }

    public List<UserAssignmentDto> getByAssignment(long assignmentId) {
        return userAssignmentDao.getByAssignment(assignmentId)
                .stream()
                .map(userAssignmentMapper::userAssignmentToUserAssignmentDto)
                .collect(Collectors.toList());
    }
}
