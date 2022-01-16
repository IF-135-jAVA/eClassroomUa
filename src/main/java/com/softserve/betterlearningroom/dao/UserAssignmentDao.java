package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.UserAssignment;

import java.util.List;

public interface UserAssignmentDao {

    UserAssignment create(UserAssignment userAssignment);

    UserAssignment readById(long id);

    UserAssignment update(UserAssignment userAssignment);

    void delete(long id);

    List<UserAssignment> getByAssignment(long assignmentId);
}
