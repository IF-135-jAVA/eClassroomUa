package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.BeLeRoApplication;
import com.softserve.betterlearningroom.dao.CommentDAO;
import com.softserve.betterlearningroom.mapper.CommentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootConfiguration(BeLeRoApplication.class)

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = {TestDBConfiguration.class, CommentDAO.class})
//@TestPropertySource(properties = {"classpath:/resourses/comment_queries.properties"})


public class CommentControllerIntegTest {

    @Mock
    private CommentDAO commentDAO;

    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        CommentMapper commentMapper = new CommentMapper();
        commentService = new CommentService(commentDAO, commentMapper);
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

//    @Test
//    void readByIdAuthorIdTest() {
//        Comment comment = new Comment(1,  "text", "date",
//                1, 2, 2, 1);
//        given(commentDAO.readByIdComments(1).willReturn(1));
//        CommentDTO commentDTO = commentService.readByIdComments(1);
//        assertEquals(commentDTO.getId(), 1);
//    }


    }
