package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    public CommentDTO readByIdComments(long id) {
        return commentMapper.commentToCommentDTO(commentDAO.readByIdComments(id));
    }

    public CommentDTO createComments(CommentDTO commentDTO) {
        commentDTO.setText(commentDTO.getText());
        commentDTO.setDate(LocalDateTime.now());
        commentDTO.setEnabled(true);
        return commentMapper.commentToCommentDTO(
                commentDAO.createComments(commentMapper.commentDTOToComment(commentDTO)));
    }

    public CommentDTO updateComments(CommentDTO commentDTO, long id) {
        CommentDTO oldCommentDTO = readByIdComments(id);
        oldCommentDTO.setText(commentDTO.getText());
        return commentMapper.commentToCommentDTO(
                commentDAO.updateComments(commentMapper.commentDTOToComment(oldCommentDTO)));
    }

    public void deleteComments(long id) {
        commentDAO.deleteComments(id);
    }

    public List<CommentDTO> readByIdMaterialComments(long materialCommentsId) {
        return commentDAO.readByIdMaterialComments(materialCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> readByIdAnnouncementComments(long announcementCommentsId) {
        return commentDAO.readByIdAnnouncementComments(announcementCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> readByIdUserAssignmentComments(long userAssignmentCommentsId) {
        return commentDAO.readByIdUserAssignmentComments(userAssignmentCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> readByIdAuthorId(long authorId) {
        return commentDAO.readByIdAuthorId(authorId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}