package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(
//        allowCredentials = "true",
//        origins = "http://localhost:4200",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
//)
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassroomController {

    private ClassroomService classroomService;

    @GetMapping("/{classroomId}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long classroomId){
        return ResponseEntity.ok().body(classroomService.getClassroomById(classroomId));
    }

    @GetMapping("/{classroomId}/owners/{userId}")
    public ResponseEntity<User> findClassroomOwnerById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomService.getClassroomOwnerById(userId));
    }

    @GetMapping("/{classroomId}/teachers")
    public ResponseEntity<List<User>> findClassroomTeachers(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomTeachers(classroomId));
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

