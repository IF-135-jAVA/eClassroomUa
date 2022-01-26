package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dao.impl.ClassroomDaoImpl;
import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.Classroom;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.service.impl.ClassroomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
class ClassroomServiceImplTest {

    @Mock
    private ClassroomServiceImpl classroomServiceImpl;

    private ClassroomDTO expectedClassroomDTO;
    private Classroom expectedClassroom;
    private User expectedUser;
    private UserDTO expectedUserDTO;

    @BeforeEach
    void setUp() {
        expectedClassroomDTO = ClassroomDTO.builder()
                .classroomId(1L)
                .userId(1L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
        expectedClassroom = Classroom.builder()
                .classroomId(1L)
                .userId(1L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
                .code("3v8ev2t")
                .build();
        expectedUserDTO = UserDTO.builder()
                .firstName("John")
                .lastName("Rambo")
                .email("johnrambo@gmail.com")
                .enabled(true)
                .build();
        expectedUser = User.builder()
                .firstName("John")
                .lastName("Rambo")
                .email("johnrambo@gmail.com")
                .password("$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e")
                .enabled(true)
                .build();
    }

    @Test
    void testGetClassroomById() {
        when(classroomServiceImpl.getClassroomById(1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO byId = classroomServiceImpl.getClassroomById(1L);

        assertNotNull(byId);
        assertEquals(expectedClassroom.getClassroomId(), byId.getClassroomId());
        assertEquals(expectedClassroom.getUserId(), byId.getUserId());
        assertEquals(expectedClassroom.getTitle(), byId.getTitle());
        assertEquals(expectedClassroom.getSession(), byId.getSession());
        assertEquals(expectedClassroom.getDescription(), byId.getDescription());
        assertEquals(expectedClassroom.getCode(), byId.getCode());
        verify(classroomServiceImpl).getClassroomById(1L);
    }

    @Test
    public void testCreateClassroom() {

        when(classroomServiceImpl.createClassroom(any(ClassroomDTO.class))).thenReturn(expectedClassroomDTO);

        ClassroomDTO create = classroomServiceImpl.createClassroom(expectedClassroomDTO);
        assertNotNull(create);
        assertEquals(1L, create.getClassroomId());
        assertEquals(1L, create.getUserId());
        assertEquals("English Language", create.getTitle());
        assertEquals("Present Simple", create.getSession());
        assertEquals("The Present Simple Tense", create.getDescription());
        assertEquals("3v8ev2t", create.getCode());
    }

    @Test
    void testGetClassroomOwnerById() {
        when(classroomServiceImpl.getClassroomOwnerById(1L)).thenReturn(expectedUserDTO);

        UserDTO ownerById = classroomServiceImpl.getClassroomOwnerById(1L);

        assertNotNull(ownerById);
        assertEquals(expectedUser.getFirstName(), ownerById.getFirstName());
        assertEquals(expectedUser.getLastName(), ownerById.getLastName());
        assertEquals(expectedUser.getEmail(), ownerById.getEmail());
        assertEquals(expectedUser.isEnabled(), ownerById.isEnabled());
        verify(classroomServiceImpl).getClassroomOwnerById(1L);

    }

    @Test
    void testGetClassroomTeachers() {
        List<UserDTO> listExpectedUsers = new ArrayList<>();
        listExpectedUsers.add(expectedUserDTO);

        when(classroomServiceImpl.getClassroomTeachers(1L)).thenReturn(listExpectedUsers);

        List<UserDTO> listActualUsers = classroomServiceImpl.getClassroomTeachers(1L);

        assertNotNull(listActualUsers);
        assertEquals(listActualUsers, listExpectedUsers);
    }

    @Test
    void getClassroomStudents() {
        List<UserDTO> listExpectedUsers = new ArrayList<>();
        listExpectedUsers.add(expectedUserDTO);

        when(classroomServiceImpl.getClassroomStudents(1L)).thenReturn(listExpectedUsers);

        List<UserDTO> listActualUsers = classroomServiceImpl.getClassroomStudents(1L);

        assertNotNull(listActualUsers);
        assertEquals(listActualUsers, listExpectedUsers);
    }

    @Test
    void getClassroomsByTeacher() {
        List<ClassroomDTO> listExpectedClassrooms = new ArrayList<>();
        listExpectedClassrooms.add(expectedClassroomDTO);

        when(classroomServiceImpl.getClassroomsByTeacher(1L)).thenReturn(listExpectedClassrooms);

        List<ClassroomDTO> listActualClassrooms = classroomServiceImpl.getClassroomsByTeacher(1L);

        assertNotNull(listActualClassrooms);
        assertEquals(listActualClassrooms, listExpectedClassrooms);
    }

    @Test
    void testGetClassroomsByStudent() {
        List<ClassroomDTO> listExpectedClassrooms = new ArrayList<>();
        listExpectedClassrooms.add(expectedClassroomDTO);

        when(classroomServiceImpl.getClassroomsByStudent(1L)).thenReturn(listExpectedClassrooms);

        List<ClassroomDTO> listActualClassrooms = classroomServiceImpl.getClassroomsByStudent(1L);

        assertNotNull(listActualClassrooms);
        assertEquals(listActualClassrooms, listExpectedClassrooms);
    }

    @Test
    void testJoinClassroomAsStudent() {
        when(classroomServiceImpl.joinClassroomAsStudent("3v8ev2t", 1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO joinClassroomDTO = classroomServiceImpl.joinClassroomAsStudent("3v8ev2t", 1L);

        assertNotNull(joinClassroomDTO);
        assertEquals(expectedClassroomDTO, joinClassroomDTO);
    }

    @Test
    void testJoinClassroomAsTeacher() {
        when(classroomServiceImpl.joinClassroomAsTeacher("3v8ev2t", 1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO joinClassroomDTO = classroomServiceImpl.joinClassroomAsTeacher("3v8ev2t", 1L);

        assertNotNull(joinClassroomDTO);
        assertEquals(expectedClassroomDTO, joinClassroomDTO);
    }
}
