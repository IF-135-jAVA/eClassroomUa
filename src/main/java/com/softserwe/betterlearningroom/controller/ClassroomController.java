package com.softserwe.betterlearningroom.controller;

import com.softserwe.betterlearningroom.model.Classroom;
import java.util.List;

import com.softserwe.betterlearningroom.model.User;
import com.softserwe.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classroom")
@AllArgsConstructor
public class ClassroomController{

    private ClassroomService classroomService;

    @GetMapping("{id}")
    private ResponseEntity<List<Classroom>> findClassroomById(@PathVariable Long classroomId){

        return ResponseEntity.ok().body(classroomService.getClassroomById(classroomId));
    }

    @GetMapping("/api/users/{userid}/classrooms/owner")
    private ResponseEntity<List<User>> findClassroomOwnerById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomOwner(classroomId));
    }

    @GetMapping("/teachers/classrooms/{classroomId}")
    public ResponseEntity<List<User>> findClassroomTeachersById(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomTeachers(classroomId));
    }

    @PostMapping()
    private ResponseEntity<?> createClassroom(@RequestBody Classroom classroom){
        classroomService.addClassroom(classroom);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
