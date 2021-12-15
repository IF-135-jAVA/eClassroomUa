package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.UserAssignmentDto;
import com.softserve.betterlearningroom.entity.UserAssignment;
import org.mapstruct.Mapper;

@Mapper
public interface UserAssignmentMapper {
    UserAssignment userAssignmentDtoToUserAssignment(UserAssignmentDto userAssignmentDto);
    UserAssignmentDto userAssignmentToUserAssignmentDto(UserAssignment userAssignment);
}
