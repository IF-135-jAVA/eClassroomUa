package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.ClassroomDAO;
import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.mapper.ClassroomMapper;
import com.softserve.betterlearningroom.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomServiceTests {

//    @Test
//    public void test_getClassroomById() {
//        ClassroomDAO classroomDAO = Mockito.mock(ClassroomDAO.class);
//        ClassroomMapper mapper = Mockito.mock(ClassroomMapper.class);
//        ClassroomService classroomService = new ClassroomService(classroomDAO, mapper);
//
//        Classroom classroom = new Classroom();
//        classroom.setDescription("qwe");
//
//        long classroomId = 1L;
//        Mockito.when(dao.getClassroomById(classroomId)).thenReturn(classroom);
//
//        ClassroomDTO classroomDTO = new ClassroomDTO();
//        Mockito.when(mapper.classroomToClassroomDTO(classroom)).thenReturn(classroomDTO);
//
//        ClassroomDTO classroomById = classroomService.getClassroomById(classroomId);
//        Assertions.assertEquals(classroomById, classroomDTO);
//    }

    @Test
    public void test_getClassroomById() {
        ClassroomDAO classroomDAO = Mockito.mock(ClassroomDAO.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ClassroomMapper mapper = new ClassroomMapper();
        ClassroomService classroomService = new ClassroomService(classroomDAO, mapper, userMapper);

        Classroom classroom = new Classroom();
        classroom.setDescription("qwe");

        long classroomId = 1L;
        Mockito.when(classroomDAO.getClassroomById(classroomId)).thenReturn(classroom);

        ClassroomDTO classroomDTO = classroomService.getClassroomById(classroomId);
        Assertions.assertEquals(classroomDTO.getDescription(), classroom.getDescription());
        Assertions.assertEquals(classroomDTO.getCode(), null);
    }

    @Test
    public void test_getClassroomOwnerById() {
        ClassroomDAO classroomDAO = Mockito.mock(ClassroomDAO.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ClassroomService classroomService = new ClassroomService(classroomDAO, new ClassroomMapper(), userMapper);

        User user = new User();
        long userId = 1L;
        Mockito.when(classroomDAO.getClassroomOwnerById(userId)).thenReturn(user);

        Assertions.assertEquals(classroomService.getClassroomOwnerById(userId), user);
        Assertions.assertEquals(classroomService.getClassroomOwnerById(userId+2), null);

    }

    @Test
    public  void test_getClassroomTeachers(){
        ClassroomDAO classroomDAO = Mockito.mock(ClassroomDAO.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ClassroomService classroomService = new ClassroomService(classroomDAO, new ClassroomMapper(), userMapper);

        List<User> userList = new ArrayList<>();
        long classroomId = 1L;
        Mockito.when(classroomDAO.getClassroomTeachers(classroomId)).thenReturn(userList);
    }

    @Test
    public void test_createClassroom() {
        MockDAO mockDAO = new MockDAO(null);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ClassroomService classroomService = new ClassroomService(mockDAO, new ClassroomMapper(), userMapper);
        int size = new Random().nextInt(10);
        for (int i = 0; i < size; i++) {
            classroomService.createClassroom(new ClassroomDTO());
        }
        Assertions.assertEquals(mockDAO.classrooms.size(), size);
    }

    @Test
    public void test_removeClassroomById(){
        MockDAO mockDAO = new MockDAO(null);
        UserMapper userMapper = Mockito.mock(UserMapper.class);
        ClassroomService classroomService = new ClassroomService(mockDAO, new ClassroomMapper(), userMapper);
        long classroomId = 1L;
        Classroom classroom = new Classroom();
        classroom.setClassroomId(classroomId);
        mockDAO.classrooms.add(classroom);
        classroomService.removeClassroomById(classroomId);
    }

    private final class MockDAO extends ClassroomDAO {

        private final List<Classroom> classrooms = new ArrayList<>();

        public MockDAO(NamedParameterJdbcTemplate jdbcParameterTemplate) {

            super(jdbcParameterTemplate);
        }

        @Override
        public Classroom getClassroomById(Long classroomId) {
            for (int i = 0; i < classrooms.size(); i++) {
                Classroom classroom = classrooms.get(i);
                if(classroom.getClassroomId() == classroomId){
                    return classroom;
                }
            }
            return null;
        }

        @Override
        public void removeClassroomById(Long classroomId) {
            Classroom classroomById = getClassroomById(classroomId);
            classrooms.remove(classroomById);
        }

        @Override
        public void createClassroom(Classroom classroom) {

            classrooms.add(classroom);
        }
    }
}
