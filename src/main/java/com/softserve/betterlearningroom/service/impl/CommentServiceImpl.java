package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import com.softserve.betterlearningroom.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    @Override
    public CommentDTO readByIdComment(long id) {
        return commentMapper.commentToCommentDTO(commentDAO.readByIdComment(id));
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
//        commentDTO.setText(commentDTO.getText());
        commentDTO.setDate(LocalDateTime.now());
        commentDTO.setEnabled(true);
        return commentMapper.commentToCommentDTO(
                commentDAO.createComment(commentMapper.commentDTOToComment(commentDTO)));
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, long id) {
        CommentDTO oldCommentDTO = readByIdComment(id);
        oldCommentDTO.setText(commentDTO.getText());
        return commentMapper.commentToCommentDTO(
                commentDAO.updateComment(commentMapper.commentDTOToComment(oldCommentDTO)));
    }

    @Override
    public void deleteComment(long id) {
        commentDAO.deleteComment(id);
    }

    @Override
    public List<CommentDTO> readByIdMaterialComments(long materialCommentsId) {
        return commentDAO.readByIdMaterialComments(materialCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdAnnouncementComments(long announcementCommentsId) {
        return commentDAO.readByIdAnnouncementComments(announcementCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdUserAssignmentComments(long userAssignmentCommentsId) {
        return commentDAO.readByIdUserAssignmentComments(userAssignmentCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdAuthorId(long authorId) {
        return commentDAO.readByIdAuthorId(authorId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}