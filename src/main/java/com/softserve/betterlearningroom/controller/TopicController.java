package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/classrooms/{classroomId}/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<List<TopicDTO>> findAll() {
        List<TopicDTO> topic = topicService.findAll();
        return ResponseEntity.ok().body(topic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> findAllByClassroomId(@PathVariable(value = "classroomId") final String classroomId) {
        List<TopicDTO> topic = topicService .findAllByClassroomId(classroomId);
        return ResponseEntity.ok().body(topic);
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicDTO> findById(@PathVariable Long id) {
        TopicDTO topicDTO = topicService.findById(id);
        return ResponseEntity.ok().body(topicDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicDTO> save(@Valid @RequestBody TopicDTO topicDTO) {
        return new ResponseEntity<>(topicService.save(topicDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final TopicDTO topicDTO) {
        return new ResponseEntity<>(topicService.update(topicDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        topicService.delete(id);
    }
}
