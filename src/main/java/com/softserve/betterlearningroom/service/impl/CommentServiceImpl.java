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
    public CommentDTO findByCommentId(Long id) {
        return commentMapper.commentToCommentDTO(commentDAO.findById(id));
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        commentDTO.setDate(LocalDateTime.now());
        commentDTO.setEnabled(true);
        return commentMapper.commentToCommentDTO(
                commentDAO.save(commentMapper.commentDTOToComment(commentDTO)));
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO, Long id) {
        CommentDTO oldCommentDTO = findByCommentId(id);
        oldCommentDTO.setText(commentDTO.getText());
        return commentMapper.commentToCommentDTO(
                commentDAO.update(commentMapper.commentDTOToComment(oldCommentDTO)));
    }

    @Override
    public void delete(Long id) {
        commentDAO.delete(id);
    }

    @Override
    public List<CommentDTO> findByMaterialId(Long materialCommentsId) {
        return commentDAO.findByMaterialId(materialCommentsId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByAnnouncementId(Long announcementId) {
        return commentDAO.findByAnnouncementId(announcementId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByUserAssignmentId(Long userAssignmentId) {
        return commentDAO.findByUserAssignmentId(userAssignmentId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findByAuthorId(Long authorId) {
        return commentDAO.findByAuthorId(authorId)
                .stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}