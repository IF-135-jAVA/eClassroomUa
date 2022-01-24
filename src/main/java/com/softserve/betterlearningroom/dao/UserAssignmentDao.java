package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.UserAssignment;

import java.util.List;

public interface UserAssignmentDao {

    UserAssignment save(UserAssignment userAssignment);

    UserAssignment findById(Long id);

    UserAssignment update(UserAssignment userAssignment);

    void delete(Long id);

    List<UserAssignment> findByAssignmentId(Long assignmentId);
}
