package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment readByIdComment(long id);

    Comment createComment(Comment comment);

    Comment updateComment(Comment updateComment);

    void deleteComment(long id);

    List<Comment> readByIdMaterialComments(long materialCommentsId);

    List<Comment> readByIdAnnouncementComments(long announcementCommentsId);

    List<Comment> readByIdUserAssignmentComments(long userAssignmentCommentsId);

    List<Comment> readByIdAuthorId(long authorId);
}
