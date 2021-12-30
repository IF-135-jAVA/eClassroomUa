package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.entity.UserAssignment;
import org.mapstruct.Mapper;

@Mapper
public interface UserAssignmentMapper {
    UserAssignment userAssignmentDTOToUserAssignment(UserAssignmentDTO userAssignmentDTO);
    UserAssignmentDTO userAssignmentToUserAssignmentDTO(UserAssignment userAssignment);
}
