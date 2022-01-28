package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    /**
     * get comment by id from the database
     *
     * @param id Long
     * @return comment by id
     */
    CommentDTO findByCommentId(Long id);

    /**
     * Create a new resource (comment) in database
     *
     * @param commentDTO CommentDTO
     * @return new comment
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * update comment by id in the database
     *
     * @param commentDTO CommentDTO
     * @param id         Long
     * @return updated comment
     */
    CommentDTO update(CommentDTO commentDTO, Long id);

    /**
     * delete comment by id, do it not active in the database
     *
     * @param id Long
     */
    void delete(Long id);

    /**
     * get all comments by material id from the database
     *
     * @param materialId Long
     * @return List Comment by material id
     */
    List<CommentDTO> findByMaterialId(Long materialId);

    /**
     * get all comments by announcement id from the database
     *
     * @param announcementId Long
     * @return List Comment by announcement id
     */
    List<CommentDTO> findByAnnouncementId(Long announcementId);

    /**
     * get all comments by userAssignment id from the database
     *
     * @param userAssignmentId Long
     * @return List Comment by userAssignment id
     */
    List<CommentDTO> findByUserAssignmentId(Long userAssignmentId);

    /**
     * get all comments by author id from the database
     *
     * @param authorId Long
     * @return List Comment by author id
     */
    List<CommentDTO> findByAuthorId(Long authorId);
}
