package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CommentController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommentController.class) })
@AutoConfigureMockMvc(addFilters = false)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentServiceImpl commentService;

    private ObjectMapper mapper;

    @Test
    void readByCommentIdTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = new CommentDTO(2L, "text2", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true);
        given(commentService.findByCommentId(Mockito.anyLong())).willReturn(comment);
        mvc.perform(get("/api/comments/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(commentService).findByCommentId(2L);
    }

    @Test
    void commentIsNotFoundTest() throws Exception {
        given(commentService.findByCommentId(Mockito.anyLong())).willThrow(DataRetrievalFailureException.class);
        assertThatThrownBy(() -> mvc.perform(get("/api/comments/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                        .hasCause(new DataRetrievalFailureException(null));
        verify(commentService).findByCommentId(0L);
    }

    @Test
    void createCommentTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = getComment();
        given(commentService.save(any(CommentDTO.class))).willReturn(comment);
        mvc.perform(MockMvcRequestBuilders.post("/api/comments/users/3").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(comment))).andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateCommentTest() throws Exception {
        mapper = new ObjectMapper();
        CommentDTO comment = getComment();
        given(commentService.update(any(CommentDTO.class), anyLong())).willReturn(comment);
        mvc.perform(put("/api/comments/2").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(comment))).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(commentService).update(any(CommentDTO.class), anyLong());
    }

    @Test
    void readByIdAuthorIdTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2L, "text2", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        commentList.add(new CommentDTO(3L, "text3","Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        given(commentService.findByAuthorId(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/comments/users/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdMaterialIdTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2L, "text2", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        commentList.add(new CommentDTO(3L, "text3", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        given(commentService.findByMaterialId(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/comments/materials/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdAnnouncementCommentsTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2L, "text2", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        commentList.add(new CommentDTO(3L, "text3", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        given(commentService.findByAnnouncementId(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/comments/announcements/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void readByIdUserAssignmentCommentsTest() throws Exception {
        List<CommentDTO> commentList = new ArrayList<>();
        commentList.add(new CommentDTO(2L, "text2", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        commentList.add(new CommentDTO(3L, "text3", "Jon", "Duo", LocalDateTime.now(), 3L, 3L, 4L, 2L, true));
        given(commentService.findByUserAssignmentId(anyLong())).willReturn(commentList);
        mvc.perform(get("/api/comments/user-assignments/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private CommentDTO getComment() {
        CommentDTO comment = CommentDTO.builder().id(1L).text("text1").authorId(2L).announcementId(3L).materialId(2L)
                .userAssignmentId(4L).enabled(true).build();
        return comment;
    }
}