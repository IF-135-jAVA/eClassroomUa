package com.softserve.betterlearningroom.service;

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
class ClassroomServiceTest {

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
                .build();
        expectedClassroom = Classroom.builder()
                .classroomId(1L)
                .userId(1L)
                .title("English Language")
                .session("Present Simple")
                .description("The Present Simple Tense")
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
    void testFindById() {
        when(classroomServiceImpl.findById(1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO byId = classroomServiceImpl.findById(1L);

        assertNotNull(byId);
        assertEquals(expectedClassroom.getClassroomId(), byId.getClassroomId());
        assertEquals(expectedClassroom.getUserId(), byId.getUserId());
        assertEquals(expectedClassroom.getTitle(), byId.getTitle());
        assertEquals(expectedClassroom.getSession(), byId.getSession());
        assertEquals(expectedClassroom.getDescription(), byId.getDescription());
        verify(classroomServiceImpl).findById(1L);
    }

    @Test
    public void testSave() {

        when(classroomServiceImpl.save(any(ClassroomDTO.class))).thenReturn(expectedClassroomDTO);

        ClassroomDTO create = classroomServiceImpl.save(expectedClassroomDTO);
        assertNotNull(create);
        assertEquals(1L, create.getClassroomId());
        assertEquals(1L, create.getUserId());
        assertEquals("English Language", create.getTitle());
        assertEquals("Present Simple", create.getSession());
        assertEquals("The Present Simple Tense", create.getDescription());
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
    void testGetClassroomTeachersById() {
        List<UserDTO> listExpectedUsers = new ArrayList<>();
        listExpectedUsers.add(expectedUserDTO);

        when(classroomServiceImpl.getClassroomTeachersById(1L)).thenReturn(listExpectedUsers);

        List<UserDTO> listActualUsers = classroomServiceImpl.getClassroomTeachersById(1L);

        assertNotNull(listActualUsers);
        assertEquals(listActualUsers, listExpectedUsers);
    }

    @Test
    void testGetClassroomStudentsById() {
        List<UserDTO> listExpectedUsers = new ArrayList<>();
        listExpectedUsers.add(expectedUserDTO);

        when(classroomServiceImpl.getClassroomStudentsById(1L)).thenReturn(listExpectedUsers);

        List<UserDTO> listActualUsers = classroomServiceImpl.getClassroomStudentsById(1L);

        assertNotNull(listActualUsers);
        assertEquals(listActualUsers, listExpectedUsers);
    }

    @Test
    void testFindAllClassroomsByTeacherId() {
        List<ClassroomDTO> listExpectedClassrooms = new ArrayList<>();
        listExpectedClassrooms.add(expectedClassroomDTO);

        when(classroomServiceImpl.findAllClassroomsByTeacherId(1L)).thenReturn(listExpectedClassrooms);

        List<ClassroomDTO> listActualClassrooms = classroomServiceImpl.findAllClassroomsByTeacherId(1L);

        assertNotNull(listActualClassrooms);
        assertEquals(listActualClassrooms, listExpectedClassrooms);
    }

    @Test
    void testFindAllClassroomsByStudentId() {
        List<ClassroomDTO> listExpectedClassrooms = new ArrayList<>();
        listExpectedClassrooms.add(expectedClassroomDTO);

        when(classroomServiceImpl.findAllClassroomsByStudentId(1L)).thenReturn(listExpectedClassrooms);

        List<ClassroomDTO> listActualClassrooms = classroomServiceImpl.findAllClassroomsByStudentId(1L);

        assertNotNull(listActualClassrooms);
        assertEquals(listActualClassrooms, listExpectedClassrooms);
    }

    @Test
    void testJoinClassroomAsStudent() {
        when(classroomServiceImpl.joinClassroomAsStudent(1L, 1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO joinClassroomDTO = classroomServiceImpl.joinClassroomAsStudent(1L, 1L);

        assertNotNull(joinClassroomDTO);
        assertEquals(expectedClassroomDTO, joinClassroomDTO);
    }

    @Test
    void testJoinClassroomAsTeacher() {
        when(classroomServiceImpl.joinClassroomAsTeacher(1L, 1L)).thenReturn(expectedClassroomDTO);

        ClassroomDTO joinClassroomDTO = classroomServiceImpl.joinClassroomAsTeacher(1L, 1L);

        assertNotNull(joinClassroomDTO);
        assertEquals(expectedClassroomDTO, joinClassroomDTO);
    }
}
