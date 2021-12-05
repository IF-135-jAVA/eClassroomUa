package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.UserAssignmentDao;
import com.softserve.betterlearningroom.entity.UserAssignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserAssignmentService {

    private UserAssignmentDao userAssignmentDao;

    public void create(UserAssignment userAssignment) {
        userAssignment.setSubmissionDate(LocalDateTime.now());
        userAssignmentDao.create(userAssignment);
    }

    public UserAssignment readById(long id) {
        List<UserAssignment> result = userAssignmentDao.readById(id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void update(UserAssignment userAssignment, long id) {
        UserAssignment oldUserAssignment = readById(id);
        if(oldUserAssignment != null) {
            oldUserAssignment.setAssignmentStatus(userAssignment.getAssignmentStatus());
            oldUserAssignment.setGrade(userAssignment.getGrade());
            oldUserAssignment.setFeedback(userAssignment.getFeedback());
            userAssignmentDao.update(oldUserAssignment);
        }
    }

    public List<UserAssignment> getByAssignment(long assignmentId) {
        return userAssignmentDao.getByAssignment(assignmentId);
    }

    public List<UserAssignment> getByStudent(long studentId) {
        return userAssignmentDao.getByStudent(studentId);
    }
}
