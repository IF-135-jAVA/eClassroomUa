package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

public interface UserAssignmentService {

    /**
     * Save the given userAssignment in the database if due date for its assignment has not passed.
     * Set the appropriate values for some fields:
     * assignmentStatus - IN_PROGRESS, submissionDate - null, grade - 0, feedback - null, enabled - true
     *
     * @param userAssignmentDTO new userAssignment
     * @return saved userAssignment
     * @throws SubmissionNotAllowedException if due date for assignment has passed
     */
    UserAssignmentDTO save(UserAssignmentDTO userAssignmentDTO);

    /**
     * Find userAssignment by id
     *
     * @param id id of userAssignment
     * @return userAssignment with the given id
     * @throws DataRetrievalFailureException if userAssignment with the given id is missing or disabled (deleted)
     */
    UserAssignmentDTO findById(Long id);

    /**
     * Update grade and feedback of userAssignment with the given id. Set the appropriate value for the field: assignmentStatus - REVIEWED
     *
     * @param userAssignmentDTO userAssignment with new values of fields
     * @param id id of userAssignment
     * @return updated userAssignment
     * @throws DataRetrievalFailureException if userAssignment with the given id is missing or disabled (deleted)
     */
    UserAssignmentDTO updateAsTeacher(UserAssignmentDTO userAssignmentDTO, Long id);

    /**
     * Update assignmentStatusId of userAssignment with the given id. Set the new value for the field: assignmentStatus - any except REVIEWED
     *
     * @param userAssignmentDTO userAssignment with the new value of the field assignmentStatusId
     * @param id id of userAssignment
     * @return updated userAssignment
     * @throws DataRetrievalFailureException if userAssignment with the given id is missing or disabled (deleted)
     */
    UserAssignmentDTO updateAsStudent(UserAssignmentDTO userAssignmentDTO, Long id);

    /**
     * Mark userAssignment with the given id and its answers as disabled in the database
     *
     * @param id id of userAssignment
     * @throws DataRetrievalFailureException if userAssignment with the given id is missing or disabled (deleted)
     */
    void delete(Long id);

    /**
     * Find enabled (not deleted) userAssignments by assignmentId.
     * If the authenticated user's role is STUDENT, filter the returned list of userAssignments by userId that should be equal to
     * id of the authenticated user
     *
     * @param assignmentId id of assignment
     * @return list of userAssignments with the given assignmentId
     */
    List<UserAssignmentDTO> findByAssignmentId(Long assignmentId);
}
