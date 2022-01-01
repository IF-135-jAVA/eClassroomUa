package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataRetrievalFailureException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CommentServiceTest {

    @Mock
    private CommentDAO commentDAO;
    private CommentServiceImpl commentService;
    private CommentMapper commentMapper;
    private Comment comment;

    @Before
    public void setUp() {
        commentMapper = new CommentMapper();
        commentService = new CommentServiceImpl(commentDAO, commentMapper);
        comment = new Comment(1, "text", LocalDateTime.now(), 1, 2, 2, 1, true);
    }

    @Test
    public void readByIdCommentsTest() {
        given(commentDAO.readByIdComment(1)).willReturn(comment);
        CommentDTO commentDTO = commentService.readByIdComment(1);
        assertEquals(commentDTO.getId(), 1);
    }

    @Test
    public void createCommentsTest() {
        given(commentDAO.createComment(any(Comment.class))).willReturn(comment);
        CommentDTO commentDTO = commentService.createComment(commentMapper.commentToCommentDTO(comment));
        assertEquals("text", commentDTO.getText());
    }

    @Test
    public void updateCommentsTest() {

    }

    @Test
    public void deleteCommentsTest() {
    }

    @Test
    public void readByIdMaterialCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(1, "text1", LocalDateTime.now(), 1, 2, 2, 1, true));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 3, 4, true));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 4, 1, 1, true));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 5, 2, 5, true));
        given(commentDAO.readByIdMaterialComments(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdMaterialComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text2", actualComments.get(1).getText());
    }

    @Test
    public void readByIdAnnouncementCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(1, "text1", LocalDateTime.now(), 1, 2, 2, 1, true));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 3, 4, true));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 4, 1, 1, true));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 5, 2, 5, true));
        given(commentDAO.readByIdAnnouncementComments(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdAnnouncementComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text1", actualComments.get(0).getText());
    }

    @Test
    public void readByIdUserAssignmentCommentsTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(1, "text1", LocalDateTime.now(), 1, 2, 2, 1, true));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 3, 4, true));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 4, 1, 1, true));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 5, 2, 5, true));
        given(commentDAO.readByIdUserAssignmentComments(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdUserAssignmentComments(3);
        assertEquals(4, actualComments.size());
        assertEquals("text4", actualComments.get(3).getText());
    }

    @Test
    public void readByIdAuthorIdTest() {
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(1, "text1", LocalDateTime.now(), 1, 2, 2, 1, true));
        commentList.add(new Comment(2, "text2", LocalDateTime.now(), 2, 3, 3, 4, true));
        commentList.add(new Comment(3, "text3", LocalDateTime.now(), 3, 4, 1, 1, true));
        commentList.add(new Comment(4, "text4", LocalDateTime.now(), 1, 5, 2, 5, true));
        given(commentDAO.readByIdAuthorId(3)).willReturn(commentList);
        List<CommentDTO> actualComments = commentService.readByIdAuthorId(3);
        assertEquals(4, actualComments.size());
        assertEquals("text3", actualComments.get(2).getText());
    }

    public void whenCommentIsNotFound_ThenThrowExceptionTest() {
        given(commentDAO.readByIdComment(Mockito.anyLong())).willReturn(null);
        assertThrows(DataRetrievalFailureException.class, () -> commentService.readByIdComment(100));
    }

    public void whenCommentIsNotFoundThenThrowExceptionTest() {
        when(commentDAO.readByIdComment(100)).thenReturn(null);
        assertThrows(DataRetrievalFailureException.class, () -> commentService.readByIdComment(100));
    }
}
