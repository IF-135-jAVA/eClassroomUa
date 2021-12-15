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
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setDate(comment.getDate());

        return commentDTO;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setDate(commentDTO.getDate());

        return comment;
    }

}




