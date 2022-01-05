package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import java.util.List;

import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassroomController {

    private ClassroomService classroomService;

    @GetMapping("/{classroomId}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long classroomId){
        return ResponseEntity.ok().body(classroomService.getClassroomById(classroomId));
    }

    @GetMapping("{classroomId}/owner")
    public ResponseEntity<UserDTO> getClassroomOwnerById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomOwnerById(classroomId));
    }

    @GetMapping("/{classroomId}/teachers")
    public ResponseEntity<List<UserDTO>> getClassroomTeachers(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomTeachers(classroomId));
    }

    @GetMapping("/byTeacher/{userId}")
    @RolesAllowed(value = { "TEACHER" })
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByTeacher(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomService.getClassroomsByTeacher(userId));
    }

    @GetMapping("/byStudent/{userId}")
    @RolesAllowed(value = { "STUDENT" })
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByStudent(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomService.getClassroomsByStudent(userId));
    }

    @PostMapping()
    public ResponseEntity<?> createClassroom(@RequestBody ClassroomDTO classroomDTO){
        classroomService.createClassroom(classroomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<?> removeClassroom(@PathVariable Long classroomId){
        classroomService.removeClassroomById(classroomId);
        return ResponseEntity.ok().build();
    }
}

