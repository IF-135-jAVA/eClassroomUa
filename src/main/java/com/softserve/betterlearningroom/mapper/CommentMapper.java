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
        commentDTO.setAuthor_id(comment.getAuthor_id());
        commentDTO.setAnnouncement_id(comment.getAnnouncement_id());
        commentDTO.setUser_assignment_id(comment.getUser_assignment_id());
        commentDTO.setMaterial_id(comment.getMaterial_id());
        return commentDTO;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getDate());
        comment.setAuthor_id(commentDTO.getAuthor_id());
        comment.setAnnouncement_id(commentDTO.getAnnouncement_id());
        comment.setUser_assignment_id(commentDTO.getUser_assignment_id());
        comment.setMaterial_id(commentDTO.getMaterial_id());
        return comment;
    }

}




