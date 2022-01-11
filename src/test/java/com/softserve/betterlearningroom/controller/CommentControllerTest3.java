package com.softserve.betterlearningroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.betterlearningroom.configuration.TestDBConfiguration1;
import com.softserve.betterlearningroom.configuration.jwt.JwtProvider;
import com.softserve.betterlearningroom.dao.extractor.CommentRowMapper;
import com.softserve.betterlearningroom.dao.impl.CommentDAOImpl;
import com.softserve.betterlearningroom.dao.impl.UserDAOImpl;
import com.softserve.betterlearningroom.dto.CommentDTO;
import com.softserve.betterlearningroom.mapper.UserMapper;
import com.softserve.betterlearningroom.service.CommentService;
import com.softserve.betterlearningroom.service.CustomUserDetailsService;
import com.softserve.betterlearningroom.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

@WebMvcTest(controllers = CommentController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(value = {CustomUserDetailsService.class, UserDAOImpl.class, CommentDAOImpl.class, TestDBConfiguration1.class, JwtProvider.class})
class CommentControllerTest3 {

    @Autowired
    public MockMvc mockMvc;


    @MockBean
    private CommentServiceImpl commentService;
    private ObjectMapper mapper;


    @Test
    void readByCommentIdTest() throws Exception {
        CommentDTO comment = new CommentDTO(2, "text2", LocalDateTime.now(),
                3, 3, 4, 2, true);
        when(commentService.readByIdComment(2)).thenReturn(comment);
        MvcResult mvcResult = mockMvc.perform(get("/api/comments/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }
//    @Test
//    void createCommentTest() throws Exception {
//        mapper = new ObjectMapper();
//        CommentDTO comment = new CommentDTO(2, "text2", LocalDateTime.now(),
//                3, 3, 4, 2, true);
//
//        when(commentService.createComment(any(CommentDTO.class))).thenReturn(comment);
//        MvcResult mvcResult = mockMvc.perform(post("/api/users/3/comments")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(comment)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }

}
