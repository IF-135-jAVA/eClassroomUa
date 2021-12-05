package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.entity.UserAssignment;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{classroomId}/materials/{materialId}/user-assignments")
@AllArgsConstructor
public class UserAssignmentController {

    private UserAssignmentService userAssignmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserAssignment userAssignment) {
        userAssignmentService.create(userAssignment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAssignment> readById(@PathVariable long id) {
        UserAssignment result = userAssignmentService.readById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody UserAssignment userAssignment) {
        if(userAssignmentService.readById(id) == null) return ResponseEntity.notFound().build();
        userAssignmentService.update(userAssignment, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserAssignment>> getByAssignment(@PathVariable long materialId) {
        return ResponseEntity.ok(userAssignmentService.getByAssignment(materialId));
    }
}
