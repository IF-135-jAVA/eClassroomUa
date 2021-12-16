package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.AnswerDto;
import com.softserve.betterlearningroom.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@RequestMapping("/api/classrooms/{classroomId}/materials/{materialId}/user-assignments/{userAssignmentId}/answers")
@AllArgsConstructor
public class AnswerController {

    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDto> create(@RequestBody AnswerDto answerDto, @PathVariable long userAssignmentId) {
        answerDto.setUserAssignmentId(userAssignmentId);
        long createdDtoId = answerService.create(answerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDtoId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AnswerDto> readById(@PathVariable long id) {
        AnswerDto result = answerService.readById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<AnswerDto> update(@PathVariable long id, @RequestBody AnswerDto answerDto) {
        if(answerService.readById(id) == null) return ResponseEntity.notFound().build();
        answerService.update(answerDto, id);
        return ResponseEntity.ok(answerService.readById(id));
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getByUserAssignment(@PathVariable long userAssignmentId) {
        return ResponseEntity.ok(answerService.getByUserAssignment(userAssignmentId));
    }
}
