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

    /**
     * get all topic
     */
    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAll() {
        List<TopicDTO> topic = topicService.findAll();
        return ResponseEntity.ok().body(topic);
    }

    /**
     * get all topic by classroom id
     */
    @GetMapping("byClassroomId/{byClassroomId}")
    public ResponseEntity<List<TopicDTO>> getAllByClassroomId(@PathVariable(value = "byClassroomId") final Long classroomId) {
        List<TopicDTO> topic = topicService .findAllByClassroomId(classroomId);
        return ResponseEntity.ok().body(topic);
    }

    /**
     * get topic by id
     */
    @GetMapping("{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable Long id) {
        TopicDTO topicDTO = topicService.findById(id);
        return ResponseEntity.ok().body(topicDTO);
    }

    /**
     * create topic
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicDTO> createTopic(@Valid @RequestBody TopicDTO topicDTO) {

        return new ResponseEntity<>(topicService.save(topicDTO), HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final TopicDTO topicDTO) {

        return new ResponseEntity<>(topicService.update(topicDTO), HttpStatus.ACCEPTED);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {

        topicService.delete(id);
    }
}
