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
    public ResponseEntity<ClassroomDTO> findClassroomById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.findById(classroomId));
    }

    @GetMapping("{classroomId}/owner")
    public ResponseEntity<UserDTO> findClassroomOwnerById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomOwnerById(classroomId));
    }

    @GetMapping("/{classroomId}/teachers")
    public ResponseEntity<List<UserDTO>> findClassroomTeachersById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomTeachersById(classroomId));
    }

    @GetMapping("/{classroomId}/students")
    public ResponseEntity<List<UserDTO>> findClassroomStudentsById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomServiceImpl.getClassroomStudentsById(classroomId));
    }

    @GetMapping("/teacher/{userId}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByTeacherId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.findAllClassroomsByTeacherId(userId));
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<List<ClassroomDTO>> findClassroomsByStudentId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.findAllClassroomsByStudentId(userId));
    }

    @GetMapping("/join/student")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsStudent(@RequestParam(value = "classroomId", required = true) Long classroomId, @RequestParam(value = "userId", required = true) Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.joinClassroomAsStudent(classroomId, userId));
    }

    @GetMapping("/join/teacher")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsTeacher(@RequestParam(value = "classroomId", required = true) Long classroomId, @RequestParam(value = "userId", required = true) Long userId) {
        return ResponseEntity.ok().body(classroomServiceImpl.joinClassroomAsTeacher(classroomId, userId));
    }

    @PostMapping()
    public ResponseEntity<ClassroomDTO> save(@RequestBody ClassroomDTO classroomDTO) {
        return new ResponseEntity<>(classroomServiceImpl.save(classroomDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Object> delete(@PathVariable Long classroomId) {
        classroomServiceImpl.delete(classroomId);
        return ResponseEntity.ok().build();
    }
}