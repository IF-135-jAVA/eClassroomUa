package com.softserve.betterlearningroom.dao;


import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.dao.impl.CommentDAOImpl;
import com.softserve.betterlearningroom.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = {TestDBConfiguration.class, CommentDAOImpl.class})
class CommentDAOTest {

    @Autowired
    private CommentDAO commentDAO;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(1)
    void readByIdCommentTest() {
        Comment comment = prepareCommentDTO();
        assertEquals((comment), commentDAO.findById(2L));
        assertEquals((comment), commentDAO.findByAuthorId(2L));
        assertEquals((comment), commentDAO.findByAnnouncementId(3L));
        assertEquals((comment), commentDAO.findByMaterialId(2L));
        assertEquals((comment), commentDAO.findByUserAssignmentId(4L));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(3)
    void readByAuthorIdCommentTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(prepareCommentDTO());
        assertEquals((commentList), commentDAO.findByAuthorId(2L));
        assertEquals((commentList), commentDAO.findByAnnouncementId(3L));
        assertEquals((commentList), commentDAO.findByMaterialId(2L));
        assertEquals((commentList), commentDAO.findByUserAssignmentId(4L));
    }

    @Test
    @Order(2)
    void createCommentTest() {
        Comment comment = prepareCommentDTO();
        Comment savedComment = commentDAO.save(comment);
        assertNotNull(savedComment);
        assertEquals("text1", savedComment.getText());
        assertEquals(2L, savedComment.getAuthorId());
        assertEquals(3L, savedComment.getAnnouncementId());
        assertEquals(4L, savedComment.getUserAssignmentId());
        assertEquals(2L, savedComment.getMaterialId());
    }


    @Test
    @Order(4)
    void updateCommentTest() {
        Comment comment = prepareCommentDTO();
        comment.setId(2);
        commentDAO.update(comment);
        assertEquals("text1", commentDAO.findById(2L).getText());
    }


    @Test
    @Order(5)
    void deleteCommentTest() {
        Comment comment = prepareCommentDTO();
        commentDAO.delete(comment.getId());
    }

    private Comment prepareCommentDTO() {
        return Comment.builder()
                .id(1)
                .text("text1")
                .authorId(2)
                .announcementId(3)
                .materialId(2)
                .userAssignmentId(4)
                .date(LocalDateTime.now())
                .enabled(true)
                .build();
    }
}
