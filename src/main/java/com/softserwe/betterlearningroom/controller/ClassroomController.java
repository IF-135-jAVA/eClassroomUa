package com.softserwe.betterlearningroom.controller;

import com.softserwe.betterlearningroom.dto.ClassroomDTO;
import java.util.List;

import com.softserwe.betterlearningroom.entity.User;
import com.softserwe.betterlearningroom.service.ClassroomService;
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

@RestController
@RequestMapping("/api/classroom")
@AllArgsConstructor
public class ClassroomController{

    private ClassroomService classroomService;

    @GetMapping("/{classroomId}")
    private ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long classroomId){
        return ResponseEntity.ok().body(classroomService.getClassroomById(classroomId));
    }

    @GetMapping("/{classroomId}/owner")
    private ResponseEntity<User> findClassroomOwnerById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomOwnerById(classroomId));
    }

    @GetMapping("/teachers/classrooms/{classroomId}")
    public ResponseEntity<List<User>> findClassroomTeachers(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomTeachers(classroomId));
    }

    @PostMapping()
    private ResponseEntity<?> createClassroom(@RequestBody ClassroomDTO classroomDTO){
        classroomService.createClassroom(classroomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{classroomId}")
    private ResponseEntity<?> removeClassroom(@PathVariable Long classroomId){
        classroomService.removeClassroomById(classroomId);
        return ResponseEntity.ok().build();
    }
}
