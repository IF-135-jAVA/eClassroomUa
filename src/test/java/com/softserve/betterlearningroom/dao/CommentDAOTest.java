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
        commentDAO.save(comment);
        assertEquals(5, commentDAO.findById(5L).getId());
        assertEquals(5, commentDAO.findByAuthorId(2L).get(1).getId());
        assertEquals("text1", commentDAO.findByAnnouncementId(3L).get(1).getText());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(3)
    void readByAuthorIdCommentTest() {
        commentDAO.save(prepareCommentDTO());
        assertEquals(2, commentDAO.findByAuthorId(2L).get(1).getId());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(4)
    void readByAnnouncementIdCommentTest() {
        commentDAO.save(prepareCommentDTO());
        assertEquals(5, commentDAO.findByAnnouncementId(3L).get(1).getId());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(5)
    void readByMaterialIdCommentTest() {
        commentDAO.save(prepareCommentDTO());
        assertEquals(5, commentDAO.findByMaterialId(2L).get(1).getId());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(6)
    void readByUserAssignmentIdCommentTest() {
        commentDAO.save(prepareCommentDTO());
        assertEquals(4, commentDAO.findByUserAssignmentId(2L).get(1).getId());
    }

    @Test
    @Order(2)
    void createCommentTest() {
        Comment comment = prepareCommentDTO();
        Comment savedComment = commentDAO.save(comment);
        assertNotNull(savedComment);
        assertEquals("text1", savedComment.getText());
        assertEquals(2, savedComment.getAuthorId());
        assertEquals(3, savedComment.getAnnouncementId());
        assertEquals(4, savedComment.getUserAssignmentId());
        assertEquals(2, savedComment.getMaterialId());
    }

    @Test
    @Order(7)
    void updateCommentTest() {
        Comment comment = prepareCommentDTO();
        comment.setId(2);
        commentDAO.update(comment);
        assertEquals("text1", commentDAO.findById(2L).getText());
    }

    @Test
    @Order(8)
    void deleteCommentTest() {
        commentDAO.delete(3L);
    }

    private Comment prepareCommentDTO() {
        return Comment.builder()
                .id(5)
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

