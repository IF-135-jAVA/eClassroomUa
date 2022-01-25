package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    
    CommentDTO findByCommentId(Long id);

    CommentDTO save(CommentDTO commentDTO);

    CommentDTO update(CommentDTO commentDTO, Long id);

    void delete(Long id);

    List<CommentDTO> findByMaterialId(Long materialId);

    List<CommentDTO> findByAnnouncementId(Long announcementId);

    List<CommentDTO> findByUserAssignmentId(Long userAssignmentId);

    List<CommentDTO> findByAuthorId(Long authorId);
}
