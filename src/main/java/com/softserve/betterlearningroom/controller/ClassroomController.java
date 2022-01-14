package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import com.softserve.betterlearningroom.entity.User;
import com.softserve.betterlearningroom.service.ClassroomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@SecurityRequirement(name = "bearerAuth")
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

