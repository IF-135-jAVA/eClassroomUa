package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Classroom;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ClassroomDAO.class})
@RunWith(MockitoJUnitRunner.class)
public class ClassroomDAOTests {

//    @Autowired
//    ClassroomDAO classroomDAO;

//    @Mock
//    private ClassroomDAO classroomDAO;

    @Test
    public void testtest1() {
        ClassroomDAO mock = Mockito.mock(ClassroomDAO.class);
        Classroom classroom = new Classroom();
        Mockito.when(mock.getClassroomById(any(Long.class))).thenReturn(classroom);

        Classroom classroomByIdFromDB = mock.getClassroomById(5L);
        Assertions.assertEquals(classroom, classroomByIdFromDB);
    }

    @Test
    public void testtest() {
        NamedParameterJdbcTemplate mock = Mockito.mock(NamedParameterJdbcTemplate.class);
        Classroom classroom = new Classroom();
        Mockito.when(mock.queryForObject(
                Mockito.any(String.class),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<Classroom>>any()
//                Mockito.any(MapSqlParameterSource.class),
//                Mockito.any(RowMapper.class)
                )
        ).thenReturn(classroom);
        ClassroomDAO classroomDAO = new ClassroomDAO(mock);

        Classroom classroomByIdFromDB = classroomDAO.getClassroomById(5L);
        Assertions.assertEquals(classroom, classroomByIdFromDB);
    }
}
