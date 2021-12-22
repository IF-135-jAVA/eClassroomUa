package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class
CommentService {
    private CommentDAO commentDAO;
    private CommentMapper commentMapper;


    public CommentDTO readByIdComments(long id) {
        List<Comment> result = commentDAO.readByIdComments(id);

        return result.isEmpty() ? null : commentMapper.commentToCommentDTO(result.get(0));

    }

    public void createComments(CommentDTO commentDTO) {
        commentDAO.createComments(commentMapper.commentDTOToComment(commentDTO));
    }

    public void updateComments(CommentDTO commentDTO, long id) {
        CommentDTO oldCommentDTO = readByIdComments(id);
        if(oldCommentDTO != null) {
            oldCommentDTO.setText(commentDTO.getText());
            commentDAO.updateComments(commentMapper.commentDTOToComment(oldCommentDTO));
        }
    }
    public void deleteComments(long id) {
        CommentDTO commentDTO = readByIdComments(id);
            commentDAO.deleteComments(commentDTO.getId());
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
}