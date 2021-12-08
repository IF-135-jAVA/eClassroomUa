package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.entity.Announcement;
import com.softserve.betterlearningroom.entity.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentDAO commentDAO;

    public void create(Comment comment) {
        comment.setText(comment.getText());
        comment.setAuthor(comment.getAuthor());
        comment.setDate(LocalDateTime.now());
        commentDAO.create(comment);
    }
    public List<Comment> readAll() {
        List<Comment> comments = commentDAO.readAll();
        return comments.isEmpty() ? new ArrayList<>() : comments;
    }
    public Comment readById(long id) {
        List<Comment> result = commentDAO.readById(id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void update(Comment comment, long id) {
        Comment oldComment = readById(id);
        if (oldComment != null) {
            oldComment.setText(comment.getText());
            oldComment.setAuthor(comment.getAuthor());
            commentDAO.update(oldComment);
        }
    }

    public void delete(long id) {
       Comment comment = readById(id);
        if (comment != null) {

           commentDAO.delete(comment.getId());

        }

    }
}


