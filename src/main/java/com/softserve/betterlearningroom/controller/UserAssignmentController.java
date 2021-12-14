package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.UserAssignmentDto;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms/{classroomId}/materials/{materialId}/user-assignments")
@AllArgsConstructor
public class UserAssignmentController {

    private UserAssignmentService userAssignmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserAssignmentDto userAssignmentDto) {
        userAssignmentService.create(userAssignmentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAssignmentDto> readById(@PathVariable long id) {
        UserAssignmentDto result = userAssignmentService.readById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody UserAssignmentDto userAssignmentDto) {
        if(userAssignmentService.readById(id) == null) return ResponseEntity.notFound().build();
        userAssignmentService.update(userAssignmentDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserAssignmentDto>> getByAssignment(@PathVariable long materialId) {
        return ResponseEntity.ok(userAssignmentService.getByAssignment(materialId));
    }
}
