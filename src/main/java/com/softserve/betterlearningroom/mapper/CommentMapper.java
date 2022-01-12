package com.softserve.betterlearningroom.mapper;


import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setDate(comment.getDate());
        commentDTO.setAuthorId(comment.getAuthorId());
        commentDTO.setAnnouncementId(comment.getAnnouncementId());
        commentDTO.setUserAssignmentId(comment.getUserAssignmentId());
        commentDTO.setMaterialId(comment.getMaterialId());
        return commentDTO;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getDate());
        comment.setAuthorId(commentDTO.getAuthorId());
        comment.setAnnouncementId(commentDTO.getAnnouncementId());
        comment.setUserAssignmentId(commentDTO.getUserAssignmentId());
        comment.setMaterialId(commentDTO.getMaterialId());
        return comment;
    }

}




