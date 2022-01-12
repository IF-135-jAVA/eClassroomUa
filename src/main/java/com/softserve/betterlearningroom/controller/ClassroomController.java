package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.ClassroomDTO;
import java.util.List;
import com.softserve.betterlearningroom.dto.UserDTO;
import com.softserve.betterlearningroom.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/{classroomId}/students")
    public ResponseEntity<List<UserDTO>> getClassroomStudents(@PathVariable Long classroomId) {
        return ResponseEntity.ok().body(classroomService.getClassroomStudents(classroomId));
    }

    @GetMapping("/byTeacher/{userId}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByTeacher(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomService.getClassroomsByTeacher(userId));
    }

    @GetMapping("/byStudent/{userId}")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByStudent(@PathVariable Long userId) {
        return ResponseEntity.ok().body(classroomService.getClassroomsByStudent(userId));
    }

    @GetMapping("/asStudent")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsStudent(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "userId", required = true) Long userId){
        return ResponseEntity.ok().body(classroomService.joinClassroomAsStudent(code, userId));
    }

    @GetMapping("/asTeacher")
    @ResponseBody
    public ResponseEntity<ClassroomDTO> joinClassroomAsTeacher(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "userId", required = true) Long userId){
        return ResponseEntity.ok().body(classroomService.joinClassroomAsTeacher(code, userId));
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

