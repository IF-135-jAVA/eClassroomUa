package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnswerDTO;
import com.softserve.betterlearningroom.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assignments/{userAssignmentId}/answers")
@AllArgsConstructor
public class AnswerController {

    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDTO> create(@RequestBody AnswerDTO answerDTO, @PathVariable long userAssignmentId) {
        answerDTO.setUserAssignmentId(userAssignmentId);
        return new ResponseEntity<>(answerService.create(answerDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnswerDTO> readById(@PathVariable long id) {
        return ResponseEntity.ok(answerService.readById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnswerDTO> update(@PathVariable long id, @RequestBody AnswerDTO answerDTO) {
        return ResponseEntity.ok(answerService.update(answerDTO, id));
    }

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getByUserAssignment(@PathVariable long userAssignmentId) {
        return ResponseEntity.ok(answerService.getByUserAssignment(userAssignmentId));
    }
}
