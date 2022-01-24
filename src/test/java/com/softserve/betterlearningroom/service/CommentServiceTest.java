package com.softserve.betterlearningroom.service;


import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = {MockitoExtension.class})
class CommentServiceTest {

    private static final long COMMENT_ID = 1;

    private static final String COMMENT_TEXT = "text1";

    private static final LocalDateTime DATE = LocalDateTime.now();

    private static final long AUTHOR_ID = 2;

    private static final long ANNOUNCEMENT_ID = 1;

    private static final long USER_ASSIGNMENT_ID = 3;

    private static final long MATERIAL_ID = 3;

    private static final boolean COMMENT_ENABLED = true;


    @Mock
    private CommentDAO commentDAO;
    private CommentServiceImpl commentService;
    private CommentMapper commentMapper;

    @BeforeEach
    void setUp() {
        commentMapper = new CommentMapper();
        commentService = new CommentServiceImpl(commentDAO, commentMapper);
    }

    @Test
    void readByCommentIdTest() {
        Comment comment = new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED);
        given(commentDAO.findById(COMMENT_ID)).willReturn(comment);
        CommentDTO commentDTO = commentService.readByIdComment(COMMENT_ID);
        assertNotNull(commentDTO);
        assertEquals(COMMENT_TEXT, commentDTO.getText());
        verify(commentDAO).findById(COMMENT_ID);
    }

    @Test
    void createCommentsTest() {
        Comment comment = new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED);
        given(commentDAO.save(any(Comment.class))).willReturn(comment);
        CommentDTO commentDTO = commentService.createComment(commentMapper.commentToCommentDTO(comment));
        assertEquals("text1", commentDTO.getText());
    }

    @Test
    void readByIdAuthorIdTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, COMMENT_ENABLED));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 2, 1, 2, 1, COMMENT_ENABLED));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 2, 3, 4, COMMENT_ENABLED));
        given(commentDAO.findByAuthorId(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdAuthorId(3);
        assertEquals(4, actualComments.size());
        assertEquals("text3", actualComments.get(2).getText());
        verify(commentDAO).findByAuthorId(3);
    }

    @Test
    void readByIdMaterialCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 4, 2, COMMENT_ENABLED));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 1, 2, 1, COMMENT_ENABLED));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 2, 3, 4, COMMENT_ENABLED));
        given(commentDAO.findByMaterialId(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdMaterialComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text3", actualComments.get(2).getText());
        verify(commentDAO).findByMaterialId(3);
    }

    @Test
    void readByIdAnnouncementCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 4, 2, COMMENT_ENABLED));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 1, 2, 1, COMMENT_ENABLED));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 2, 3, 4, COMMENT_ENABLED));
        given(commentDAO.findByAnnouncementId(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdAnnouncementComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text3", actualComments.get(2).getText());
        verify(commentDAO).findByAnnouncementId(3);
    }

    @Test
    void readByIdUserAssignmentCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID, ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 4, 2, COMMENT_ENABLED));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 1, 2, 1, COMMENT_ENABLED));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 2, 3, 4, COMMENT_ENABLED));
        given(commentDAO.findByUserAssignmentId(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdUserAssignmentComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text3", actualComments.get(2).getText());
        verify(commentDAO).findByUserAssignmentId(3);
    }

    @Test
    void updateCommentsTest() {
    }

    @Test
    void deleteCommentsTest() {
        Comment comment = new Comment(COMMENT_ID, COMMENT_TEXT, DATE, AUTHOR_ID,
                ANNOUNCEMENT_ID, USER_ASSIGNMENT_ID, MATERIAL_ID, COMMENT_ENABLED);
        commentService.deleteComment(comment.getId());
    }

//    @AfterEach
//    void tearDown() {
//        verifyNoMoreInteractions(commentDAO);
//    }

}


