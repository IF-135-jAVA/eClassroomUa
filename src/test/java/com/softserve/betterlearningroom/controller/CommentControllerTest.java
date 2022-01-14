package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.impl.CommentDAOImpl;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import com.softserve.betterlearningroom.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(value = {CustomUserDetailsService.class, UserDAOImpl.class, CommentDAOImpl.class, TestDBConfiguration.class, JwtProvider.class})
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentServiceImpl commentService;

    private ObjectMapper mapper;

    @Test
    void readByCommentIdTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = new CommentDTO(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, true);
        given(commentService.readByIdComment(Mockito.anyLong())).willReturn(comment);
        ResultActions result = mvc.perform(get("/api/comments/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(commentService).readByIdComment(2);
    }

    @Test
    void commentIsNotFoundTest() throws Exception {
        given(commentService.readByIdComment(Mockito.anyLong())).willThrow(DataRetrievalFailureException.class);
        mvc.perform(get("/api/comments/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(commentService).readByIdComment(0);
    }

    @Test
    void createCommentTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = getComment();
        given(commentService.createComment(any(CommentDTO.class))).willReturn(comment);
        mvc.perform(MockMvcRequestBuilders.post("/api/users/3/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(comment)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateCommentTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = getComment();
        given(commentService.updateComment(any(CommentDTO.class), anyLong())).willReturn(comment);
        mvc.perform(put("/api/comments/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(comment)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(commentService).updateComment(any(CommentDTO.class), anyLong());
    }

    @Test
    void readByIdAuthorIdTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, true));
        commentList.add(new CommentDTO(3, "text3", LocalDateTime.now(), 3, 3, 4, 2, true));
        given(commentService.readByIdAuthorId(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/users/2/comments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdMaterialIdTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, true));
        commentList.add(new CommentDTO(3, "text3", LocalDateTime.now(), 3, 3, 4, 2, true));
        given(commentService.readByIdMaterialComments(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/materials/2/materialComments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdAnnouncementCommentsTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, true));
        commentList.add(new CommentDTO(3, "text3", LocalDateTime.now(), 3, 3, 4, 2, true));
        given(commentService.readByIdAnnouncementComments(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/announcements/3/announcementComments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdUserAssignmentCommentsTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2, "text2", LocalDateTime.now(), 3, 3, 4, 2, true));
        commentList.add(new CommentDTO(3, "text3", LocalDateTime.now(), 3, 3, 4, 2, true));
        given(commentService.readByIdUserAssignmentComments(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/user-assignments/4/userAssignmentComments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private CommentDTO getComment() {
        CommentDTO comment = CommentDTO.builder()
                .id(1)
                .text("text1")
                .authorId(2)
                .announcementId(3)
                .materialId(2)
                .userAssignmentId(4)
                .enabled(true)
                .build();
        return comment;
    }
}