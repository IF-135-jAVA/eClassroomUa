package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.exception.SubmissionNotAllowedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

public interface AnswerService {

    /**
     * Save the given answer in the database if its userAssignment is enabled (not deleted) and due date for its assignment has not passed.
     * Set the appropriate value for the field: enabled - true. Set submissionDate of its userAssignment to the current date-time
     *
     * @param answerDTO new answer
     * @return saved answer
     * @throws DataIntegrityViolationException if userAssignment of new answer is missing or disabled (deleted)
     * @throws SubmissionNotAllowedException if due date for assignment has passed
     */
    AnswerDTO save(AnswerDTO answerDTO);

    /**
     * Find answer by id
     *
     * @param id id of answer
     * @return answer with the given id
     * @throws DataRetrievalFailureException if answer with the given id is missing or disabled (deleted)
     */
    AnswerDTO findById(Long id);

    /**
     * Update text of answer with the given id if due date for its assignment has not passed.
     * Set submissionDate of its userAssignment to the current date-time
     *
     * @param answerDTO answer with the new value of the field text
     * @param id id of answer
     * @return updated answer
     * @throws DataRetrievalFailureException if answer with the given id is missing or disabled (deleted)
     * @throws SubmissionNotAllowedException if due date for assignment has passed
     */
    AnswerDTO update(AnswerDTO answerDTO, Long id);

    /**
     * Mark answer with the given id as disabled in the database if due date for its assignment has not passed.
     * Set submissionDate of its userAssignment to the current date-time
     *
     * @param id id of answer
     * @throws DataRetrievalFailureException if answer with the given id is missing or disabled (deleted)
     * @throws SubmissionNotAllowedException if due date for assignment has passed
     */
    void delete(Long id);

    /**
     * Find enabled (not deleted) answers by userAssignmentId
     *
     * @param userAssignmentId id of userAssignment
     * @return list of answers with the given userAssignmentId
     */
    List<AnswerDTO> findByUserAssignmentId(Long userAssignmentId);
}
