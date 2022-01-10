package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dao.impl.CommentDAOImpl;
import com.softserve.betterlearningroom.entity.Comment;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentDAOImpl commentDAO;


    @Before
    public void setUp() {
        CommentMapper commentMapper = new CommentMapper();
        //commentService = new CommentServiceImpl(commentDAO, commentMapper);
        Comment comment = new Comment(1, "text", LocalDateTime.now(), 1, 2, 2, 1, true);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        when(commentDAO.readByIdAuthorId(1L)).thenReturn(comments);
    }


    @Test
    void readByIdComments() {
    }

    @Test
    void createComments() {
    }

    @Test
    void updateComments() {
    }

    @Test
    void deleteComments() {
    }

    @Test
    void readByIdMaterialComments() {
    }

    @Test
    void readByIdAnnouncementComments() {
    }

    @Test
    void readByIdUserAssignmentComments() {
    }

    @Test
    void readByIdAuthorIdTest() throws Exception {
        mockMvc.perform(get("/users/{userId}/comments"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].text", is("text")));

        verify(commentDAO, times(1)).readByIdAuthorId(1L);
    }

}
