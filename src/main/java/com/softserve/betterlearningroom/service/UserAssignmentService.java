package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;

import java.util.List;

public interface UserAssignmentService {

    UserAssignmentDTO create(UserAssignmentDTO userAssignmentDTO);

    UserAssignmentDTO readById(long id);

    UserAssignmentDTO update(UserAssignmentDTO userAssignmentDTO, long id);

    void delete(long id);

    List<UserAssignmentDTO> getByAssignment(long assignmentId);
}
