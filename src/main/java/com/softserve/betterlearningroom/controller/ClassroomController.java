package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.impl.ClassroomServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassroomController {

    private ClassroomServiceImpl classroomServiceImpl;

    @GetMapping("/{classroomId}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomById(classroomId));
    }

    @GetMapping("{classroomId}/owner")
    public ResponseEntity<UserDTO> getClassroomOwnerById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomOwnerById(classroomId));
    }

    @GetMapping("/{classroomId}/teachers")
    public ResponseEntity<List<UserDTO>> getClassroomTeachers(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomTeachers(classroomId));
    }

    @GetMapping("/{classroomId}/students")
    public ResponseEntity<List<UserDTO>> getClassroomStudents(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomStudents(classroomId));
    }

    @GetMapping("/teacher/{userId}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByTeacher(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomsByTeacher(userId));
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByStudent(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomsByStudent(userId));
    }

    @GetMapping("/join/student")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsStudent(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "userId", required = true) Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.joinClassroomAsStudent(code, userId));
    }

    @GetMapping("/join/teacher")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsTeacher(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "userId", required = true) Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.joinClassroomAsTeacher(code, userId));
    }

    @PostMapping()
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO) {
        return new ResponseEntity<>(classroomServiceImpl.createClassroom(classroomDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<?> removeClassroom(@PathVariable Long classroomId) {
        classroomServiceImpl.removeClassroomById(classroomId);
        return ResponseEntity.ok().build();
    }
}

