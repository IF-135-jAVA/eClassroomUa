package com.softserve.betterlearningroom.mapper;


import com.softserve.betterlearningroom.dto.CommentDTO;

import com.softserve.betterlearningroom.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public static CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setDate(comment.getDate());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setAnnouncement(comment.getAnnouncement());
        commentDTO.setAssignment(comment.getAssignment());
        commentDTO.setMaterial(comment.getMaterial());
        return commentDTO;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getDate());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setAnnouncement(commentDTO.getAnnouncement());
        comment.setAssignment(commentDTO.getAssignment());
        comment.setMaterial(commentDTO.getMaterial());
        return comment;
    }

}




