package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.controller.CommentController;
import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)

 class CommentControllerTest2 {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
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


//    @Test
//    public void readByIdComments() throws Exception {
//        when(commentService.readByIdComment(1)).thenReturn(comment);
//       MvcResult mvcResult =  mockMvc.perform(get("/api/comments/{id}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

//    @Test
//    public void createComments() throws Exception {
//       CommentDTO commentDTO =
//               //new Comment(1, "text", LocalDateTime.now(), 1, 2, 2, 1, true);
//
//    }

    @Test
    public void updateComments() {
    }

    @Test
    public void deleteComments() {
    }

    @Test
    public void readByIdMaterialComments() {
    }

    @Test
    public void readByIdAnnouncementComments() {
    }

    @Test
    public void readByIdUserAssignmentComments() {
    }

    @Test
    public void readByIdAuthorIdTest() throws Exception {

    }

}
