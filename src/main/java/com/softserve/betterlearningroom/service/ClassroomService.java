package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;

import java.util.List;

public interface ClassroomService {

    /**
     * get classroom by id from the database
     *
     * @param classroomId Long
     * @return classroom by id
     */
    ClassroomDTO findById(Long classroomId);

    /**
     * create a new resource (classroom) in the database
     *
     * @param classroomDTO ClassroomDTO
     * @return new classroom
     */
    ClassroomDTO save(ClassroomDTO classroomDTO);

    /**
     * get owner of classroom by classroom id from the database
     *
     * @param classroomId Long
     * @return User by classroom id
     */
    UserDTO getClassroomOwnerById(Long classroomId);

    /**
     * get all classroom teachers by classroom id from the database
     *
     * @param classroomId Long
     * @return List User by classroom id
     */
    List<UserDTO> getClassroomTeachersById(Long classroomId);

    /**
     * get all classroom students by classroom id from the database
     *
     * @param classroomId Long
     * @return List User by classroom id
     */
    List<UserDTO> getClassroomStudentsById(Long classroomId);

    /**
     * get all classrooms by user(teacher) id from the database
     *
     * @param userId Long
     * @return List Classroom by user id
     */
    List<ClassroomDTO> findAllClassroomsByTeacherId(Long userId);

    /**
     * get all classrooms by user(student) id from the database
     *
     * @param userId Long
     * @return List Classroom by user id
     */
    List<ClassroomDTO> findAllClassroomsByStudentId(Long userId);

    /**
     * user joins like a student by classroom code and user id to classroom
     *
     * @param classroomId   Long
     * @param userId Long
     * @return classroom to which was joined
     */
    ClassroomDTO joinClassroomAsStudent(Long classroomId, Long userId);

    /**
     * user joins like a teacher by classroom code and user id to classroom
     *
     * @param classroomId   Long
     * @param userId Long
     * @return classroom to which was joined
     */
    ClassroomDTO joinClassroomAsTeacher(Long classroomId, Long userId);

    /**
     * delete classroom by id, do it not active in the database
     *
     * @param classroomId Long
     */
    void delete(Long classroomId);
}
