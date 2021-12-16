package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.service.impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/topic/")
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
    public ResponseEntity<Topic> getById(@PathVariable Integer id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok().body(topic);
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
