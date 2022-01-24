package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment updatedComment);

    void delete(Long id);

    List<Comment> findByMaterialId(Long materialId);

    List<Comment> findByAnnouncementId(Long announcementId);

    List<Comment> findByUserAssignmentId(Long userAssignmentId);

    List<Comment> findByAuthorId(Long authorId);
}
