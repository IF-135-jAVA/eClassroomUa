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

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(1)
    void readByIdCommentTest() {
        Comment comment = prepareCommentDTO();
        commentDAO.createComment(comment);
        assertEquals(5, commentDAO.readByIdComment(5).getId());
        assertEquals(5, commentDAO.readByIdAuthorId(2).get(1).getId());
        assertEquals("text1", commentDAO.readByIdAnnouncementComments(3).get(1).getText());
//        assertEquals((comment), commentDAO.readByIdMaterialComments(2));
//        assertEquals((comment), commentDAO.readByIdUserAssignmentComments(4));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    @Order(3)
    void readByAuthorIdCommentTest() {
//        List<Comment> commentList = new ArrayList<Comment>();
//        commentList.add(prepareCommentDTO());
        commentDAO.createComment(prepareCommentDTO());

        assertEquals(2, commentDAO.readByIdAuthorId(2).get(1).getId());
//        assertEquals((commentList), commentDAO.readByIdAnnouncementComments(3));
//        assertEquals((commentList), commentDAO.readByIdMaterialComments(2));
//        assertEquals((commentList), commentDAO.readByIdUserAssignmentComments(4));
    }

    @Test
    @Order(2)
    void createCommentTest() {
        Comment comment = prepareCommentDTO();
        Comment savedComment = commentDAO.createComment(comment);
        assertNotNull(savedComment);
        assertEquals("text1", savedComment.getText());
        assertEquals(2, savedComment.getAuthorId());
        assertEquals(3, savedComment.getAnnouncementId());
        assertEquals(4, savedComment.getUserAssignmentId());
        assertEquals(2, savedComment.getMaterialId());
    }


    @Test
    @Order(4)
    void updateCommentTest() {
        Comment comment = prepareCommentDTO();
        comment.setId(2);
        commentDAO.updateComment(comment);
        assertEquals("text1", commentDAO.readByIdComment(2).getText());
    }


    @Test
    @Order(5)
    void deleteCommentTest() {
        Comment comment = prepareCommentDTO();
        commentDAO.deleteComment(2);
    }


}
