package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.service.impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("classrooms/{classroomId}/topics/")
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
     *
     * get topic by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable Integer id) {
        TopicDTO topicDTO = topicService.findById(id);
        return ResponseEntity.ok().body(topicDTO);
    }
    /**
     *
     * create topic
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicDTO topicDTO) {
        topicService.save(topicDTO);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final TopicDTO topicDTO) {
        topicService.update(topicDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    /**
     *
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final int id) {

        topicService.removeById(id);
    }
}
