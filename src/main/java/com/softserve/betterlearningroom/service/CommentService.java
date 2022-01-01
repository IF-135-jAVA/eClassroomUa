package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO readByIdComment(long id);

    CommentDTO createComment(CommentDTO commentDTO);

    CommentDTO updateComment(CommentDTO commentDTO, long id);

    void deleteComment(long id);

    List<CommentDTO> readByIdMaterialComments(long materialCommentsId);

    List<CommentDTO> readByIdAnnouncementComments(long announcementCommentsId);

    List<CommentDTO> readByIdUserAssignmentComments(long userAssignmentCommentsId);

    List<CommentDTO> readByIdAuthorId(long authorId);
}
