package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;

import java.util.List;

public interface UserAssignmentService {

    UserAssignmentDTO save(UserAssignmentDTO userAssignmentDTO);

    UserAssignmentDTO findById(Long id);

    UserAssignmentDTO update(UserAssignmentDTO userAssignmentDTO, Long id);

    void delete(Long id);

    List<UserAssignmentDTO> findByAssignmentId(Long assignmentId);
}
