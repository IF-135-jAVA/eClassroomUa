package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.UserAssignmentDTO;
import com.softserve.betterlearningroom.service.UserAssignmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/materials/{materialId}/assignments")
@AllArgsConstructor
public class UserAssignmentController {
    private UserAssignmentService userAssignmentService;

    @PostMapping
    public ResponseEntity<UserAssignmentDTO> save(@RequestBody UserAssignmentDTO userAssignmentDTO, @PathVariable Long materialId) {
        userAssignmentDTO.setMaterialId(materialId);
        return new ResponseEntity<>(userAssignmentService.save(userAssignmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAssignmentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userAssignmentService.findById(id));
    }

    @PutMapping("{id}/evaluate")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<UserAssignmentDTO> updateAsTeacher(@PathVariable Long id, @RequestBody UserAssignmentDTO userAssignmentDTO) {
        return ResponseEntity.ok(userAssignmentService.updateAsTeacher(userAssignmentDTO, id));
    }

    @PutMapping("{id}")
    @PreAuthorize("@userAssignmentServiceImpl.findById(#id).userId.equals(authentication.principal.getId())")
    public ResponseEntity<UserAssignmentDTO> updateAsStudent(@PathVariable Long id, @RequestBody UserAssignmentDTO userAssignmentDTO) {
        return ResponseEntity.ok(userAssignmentService.updateAsStudent(userAssignmentDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserAssignmentDTO> delete(@PathVariable Long id) {
        userAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserAssignmentDTO>> findByAssignmentId(@PathVariable Long materialId) {
        return ResponseEntity.ok(userAssignmentService.findByAssignmentId(materialId));
    }
}
