package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import java.util.List;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassroomController {

    private ClassroomService classroomService;

    @GetMapping("/{classroom_id}")
    private ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long classroom_id){
        return ResponseEntity.ok().body(classroomService.getClassroomById(classroom_id));
    }

    @GetMapping("/{classroom_id}/user_id")
    private ResponseEntity<User> findClassroomOwnerById(@PathVariable Long user_id) {
        return ResponseEntity.ok().body(classroomService.getClassroomOwnerById(user_id));
    }

    @GetMapping("/{classroomId}/teachers")
    public ResponseEntity<List<User>> findClassroomTeachers(@PathVariable Long classroom_id) {
        return ResponseEntity.ok().body(classroomService.getClassroomTeachers(classroom_id));
    }

    @PostMapping()
    private ResponseEntity<?> createClassroom(@RequestBody ClassroomDTO classroomDTO){
        classroomService.createClassroom(classroomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{classroom_id}")
    private ResponseEntity<?> removeClassroom(@PathVariable Long classroom_id){
        classroomService.removeClassroomById(classroom_id);
        return ResponseEntity.ok().build();
    }
}

