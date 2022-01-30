package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.service.AnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/assignments/{userAssignmentId}/answers")
@AllArgsConstructor
public class AnswerController {
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDTO> save(@RequestBody AnswerDTO answerDTO, @PathVariable long userAssignmentId) {
        answerDTO.setUserAssignmentId(userAssignmentId);
        return new ResponseEntity<>(answerService.save(answerDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnswerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnswerDTO> update(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        return ResponseEntity.ok(answerService.update(answerDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AnswerDTO> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> findByUserAssignmentId(@PathVariable Long userAssignmentId) {
        return ResponseEntity.ok(answerService.findByUserAssignmentId(userAssignmentId));
    }
}
