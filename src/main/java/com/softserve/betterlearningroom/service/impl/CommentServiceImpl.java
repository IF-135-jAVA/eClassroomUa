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
        return commentMapper.commentToCommentDTO(commentDAO.findById(id));
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        commentDTO.setDate(LocalDateTime.now());
        commentDTO.setEnabled(true);
        return commentMapper.commentToCommentDTO(
                commentDAO.save(commentMapper.commentDTOToComment(commentDTO)));
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, long id) {
        CommentDTO oldCommentDTO = readByIdComment(id);
        oldCommentDTO.setText(commentDTO.getText());
        return commentMapper.commentToCommentDTO(
                commentDAO.update(commentMapper.commentDTOToComment(oldCommentDTO)));
    }

    @Override
    public void deleteComment(long id) {
        commentDAO.delete(id);
    }

    @Override
    public List<CommentDTO> readByIdMaterialComments(long materialCommentsId) {
        return commentDAO.findByMaterialId(materialCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdAnnouncementComments(long announcementCommentsId) {
        return commentDAO.findByAnnouncementId(announcementCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdUserAssignmentComments(long userAssignmentCommentsId) {
        return commentDAO.findByUserAssignmentId(userAssignmentCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> readByIdAuthorId(long authorId) {
        return commentDAO.findByAuthorId(authorId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}